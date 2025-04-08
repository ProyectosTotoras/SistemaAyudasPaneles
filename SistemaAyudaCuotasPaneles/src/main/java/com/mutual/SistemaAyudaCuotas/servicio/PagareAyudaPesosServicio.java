package com.mutual.SistemaAyudaCuotas.servicio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mutual.SistemaAyudaCuotas.dto.GarantiaInfoDTO;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.utilidades.Conexion;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class PagareAyudaPesosServicio {

	Conexion con = new Conexion();
	
	public void generarReporte(AyudaPesos ayudaPesos, BigDecimal montoCuota, Date fechaVto1Cuota, GarantiaInfoDTO garantiaDTO, 
	        boolean opExenta, int zano, String fechaSellado, BigDecimal interesAcumulado) {
	    
	    int longitudTotalDeseada = 122;
	    
	    try {
	        
	        String rutaReporte = "src/main/resources/Reportes/PagareAyudaPesos.jrxml";
	        JasperReport jasperReport = JasperCompileManager.compileReport(rutaReporte);
//	        System.out.println("PUNTO 1 - Reporte compilado correctamente");
//	        System.out.println("MONTOcUOTA" + montoCuota);
	        
	        // Parametros
	        Map<String, Object> parametros = new HashMap<>();
	        
	        parametros.put("PfechaHoy", formatearFechaDDSMMSYYYY(new Date()));
//	        System.out.println("Parametro PfechaHoy: " + parametros.get("PfechaHoy"));
	        
	        parametros.put("PfechaHoyFormateada", formatearFechaHoy());
//	        System.out.println("Parametro PfechaHoyFormateada: " + parametros.get("PfechaHoyFormateada"));
	        
	        parametros.put("Pnovacion", ayudaPesos.getNovacion());
//	        System.out.println("Parametro Pnovacion: " + parametros.get("Pnovacion"));
	        
	        parametros.put("Pexento", opExenta ? "E" : "NE");
//	        System.out.println("Parametro Pexento: " + parametros.get("Pexento"));
	        
	        parametros.put("PmaximoAnio", zano == 5 ? "cinco" : "diez");
//	        System.out.println("Parametro PmaximoAnio: " + parametros.get("PmaximoAnio"));
	        
	        parametros.put("PfechaDepositarFormateada", fechaSellado);
//	        System.out.println("Parametro PfechaDepositarFormateada: " + parametros.get("PfechaDepositarFormateada"));
	        
//	        System.out.println("PUNTO 2");
	        // Datos Ayuda
	        parametros.put("PnroAyuda", ayudaPesos.getNroAyuda());
//	        System.out.println("Parametro PnroAyuda: " + parametros.get("PnroAyuda"));
	        
	        parametros.put("PmontoSolicitado", formatearBigDecimal(ayudaPesos.getMontoSolicitado()));
//	        System.out.println("Parametro PmontoSolicitado: " + parametros.get("PmontoSolicitado"));
	        
	        parametros.put("PcantidadCuotas", String.valueOf(ayudaPesos.getCantidadCuotas()));
//	        System.out.println("Parametro PcantidadCuotas: " + parametros.get("PcantidadCuotas"));
	        
	        parametros.put("PmontoEnPalabras", rellenar(convertirImporteEnLetras(montoCuota), longitudTotalDeseada));
//	        System.out.println("Parametro PmontoEnPalabras: " + parametros.get("PmontoEnPalabras"));
	        
	        BigDecimal importeAcordado = ayudaPesos.getMontoSolicitado()
	                .add(ayudaPesos.getMontoGastoAdministrativo())
	                .add(interesAcumulado)
	                .add(ayudaPesos.getMontoSelladoPcial());
	        parametros.put("PmontoAyuda", formatearBigDecimal(importeAcordado));
//	        System.out.println("Parametro PmontoAyuda: " + parametros.get("PmontoAyuda"));
	        
	        parametros.put("PmontoAyudaEnPalabras", rellenar(convertirImporteEnLetras(importeAcordado), longitudTotalDeseada));
//	        System.out.println("Parametro PmontoAyudaEnPalabras: " + parametros.get("PmontoAyudaEnPalabras"));
	        
	        parametros.put("PdestinoAyuda", ayudaPesos.getDestinoAyuda());
//	        System.out.println("Parametro PdestinoAyuda: " + parametros.get("PdestinoAyuda"));
	        
	        parametros.put("PmontoCuota", formatearBigDecimal(montoCuota));
//	        System.out.println("Parametro PmontoCuota: " + parametros.get("PmontoCuota"));
	        
	        parametros.put("PgastosAdmin", formatearBigDecimal(ayudaPesos.getMontoGastoAdministrativo()));
//	        System.out.println("Parametro PgastosAdmin: " + parametros.get("PgastosAdmin"));
	        
	        parametros.put("PimporteEnPalzo", formatearBigDecimal(interesAcumulado));
//	        System.out.println("Parametro PimporteEnPalzo: " + parametros.get("PimporteEnPalzo"));
	        
	        BigDecimal importeTotal1 = ayudaPesos.getMontoSelladoPcial()
	                .add(ayudaPesos.getMontoGastoAdministrativo())
	                .add(interesAcumulado);
	        parametros.put("Ptotal1", formatearBigDecimal(importeTotal1));
//	        System.out.println("Parametro Ptotal1: " + parametros.get("Ptotal1"));
	        
	        parametros.put("PfechaVencimiento1Cuota", formatearFecha(convertirDateToLocalDate(fechaVto1Cuota)));
//	        System.out.println("Parametro PfechaVencimiento1Cuota: " + parametros.get("PfechaVencimiento1Cuota"));
	        
	        parametros.put("PfechaPago1Cuota", formatearFechaDDSMMSYYYY(fechaVto1Cuota));
//	        System.out.println("Parametro PfechaPago1Cuota: " + parametros.get("PfechaPago1Cuota"));
	        
	        parametros.put("PmontoSelladoPcial", (ayudaPesos.getMontoSelladoPcial() == null 
	                || ayudaPesos.getMontoSelladoPcial().compareTo(BigDecimal.ZERO) == 0) 
	                ? "$ 0,00": formatearBigDecimal(ayudaPesos.getMontoSelladoPcial()));
//	        System.out.println("Parametro PmontoSelladoPcial: " + parametros.get("PmontoSelladoPcial"));
	        
	        parametros.put("PmontoSellado", formatearBigDecimal(ayudaPesos.getMontoSellado()));
//	        System.out.println("Parametro PmontoSellado: " + parametros.get("PmontoSellado"));
	        
	        parametros.put("Pefectivo", (ayudaPesos.getMontoEfectivo() == null 
	                || ayudaPesos.getMontoEfectivo().compareTo(BigDecimal.ZERO) == 0)
	                ? "$ 0,00" : formatearBigDecimal(ayudaPesos.getMontoEfectivo()));
//	        System.out.println("Parametro Pefectivo: " + parametros.get("Pefectivo"));
	        
	        parametros.put("Ptransferencia", (ayudaPesos.getMontoTransferencia() == null 
	                || ayudaPesos.getMontoTransferencia().compareTo(BigDecimal.ZERO) == 0)
	                ? "$ 0,00" : formatearBigDecimal(ayudaPesos.getMontoTransferencia()));
//	        System.out.println("Parametro Ptransferencia: " + parametros.get("Ptransferencia"));
	        
	        BigDecimal cheques = ayudaPesos.getMontoCheque1()
	                .add(ayudaPesos.getMontoCheque2())
	                .add(ayudaPesos.getMontoCheque3());
	        parametros.put("Pcheques", formatearBigDecimal(cheques));
//	        System.out.println("Parametro Pcheques: " + parametros.get("Pcheques"));
	        
	        parametros.put("PnroCheque1", (ayudaPesos.getNroCheque1() == null 
	                || ayudaPesos.getNroCheque1().compareTo(0) == 0) 
	                ? "0" : String.valueOf(ayudaPesos.getNroCheque1()));
//	        System.out.println("Parametro PnroCheque1: " + parametros.get("PnroCheque1"));
	        
	        parametros.put("PnroCheque2", (ayudaPesos.getNroCheque2() == null 
	                || ayudaPesos.getNroCheque2().compareTo(0) == 0) 
	                ? "0" : String.valueOf(ayudaPesos.getNroCheque2()));
//	        System.out.println("Parametro PnroCheque2: " + parametros.get("PnroCheque2"));
	        
	        parametros.put("PnroCheque3", (ayudaPesos.getNroCheque3() == null 
	                || ayudaPesos.getNroCheque3().compareTo(0) == 0) 
	                ? "0" : String.valueOf(ayudaPesos.getNroCheque3()));
//	        System.out.println("Parametro PnroCheque3: " + parametros.get("PnroCheque3"));
	        
	        parametros.put("Pbanco1", ayudaPesos.getBancoCheque1() != null ? ayudaPesos.getBancoCheque1() : "");
//	        System.out.println("Parametro Pbanco1: " + parametros.get("Pbanco1"));
	        
	        parametros.put("Pbanco2", ayudaPesos.getBancoCheque2() != null ? ayudaPesos.getBancoCheque2() : "");
//	        System.out.println("Parametro Pbanco2: " + parametros.get("Pbanco2"));
	        
	        parametros.put("Pbanco3", ayudaPesos.getBancoCheque3() != null ? ayudaPesos.getBancoCheque3() : "");
//	        System.out.println("Parametro Pbanco3: " + parametros.get("Pbanco3"));
	        
	        parametros.put("Pcheque1", (ayudaPesos.getMontoCheque1() == null 
	                || ayudaPesos.getMontoCheque1().compareTo(BigDecimal.ZERO) == 0)
	                ? "$ 0,00" : formatearBigDecimal(ayudaPesos.getMontoCheque1()));
//	        System.out.println("Parametro Pcheque1: " + parametros.get("Pcheque1"));
	        
	        parametros.put("Pcheque2", (ayudaPesos.getMontoCheque2() == null 
	                || ayudaPesos.getMontoCheque2().compareTo(BigDecimal.ZERO) == 0)
	                ? "$ 0,00" : formatearBigDecimal(ayudaPesos.getMontoCheque2()));
//	        System.out.println("Parametro Pcheque2: " + parametros.get("Pcheque2"));
	        
	        parametros.put("Pcheque3", (ayudaPesos.getMontoCheque3() == null 
	                || ayudaPesos.getMontoCheque3().compareTo(BigDecimal.ZERO) == 0)
	                ? "$ 0,00" : formatearBigDecimal(ayudaPesos.getMontoCheque3()));
//	        System.out.println("Parametro Pcheque3: " + parametros.get("Pcheque3"));
	        
//	        System.out.println("PUNTO 3");
	        // Datos Socio
	        
	        parametros.put("PnroSocio", ayudaPesos.getNumeroSocio());
//	        System.out.println("Parametro PnroSocio: " + parametros.get("PnroSocio"));
	        
	        parametros.put("PnombreSocio", garantiaDTO.getNombreSocio());
//	        System.out.println("Parametro PnombreSocio: " + parametros.get("PnombreSocio"));
	        
	        parametros.put("PdomicilioSocio", garantiaDTO.getDireccionSocio());
//	        System.out.println("Parametro PdomicilioSocio: " + parametros.get("PdomicilioSocio"));
	        
	        parametros.put("PlocalidadSocio", garantiaDTO.getLocalidadSocio());
//	        System.out.println("Parametro PlocalidadSocio: " + parametros.get("PlocalidadSocio"));
	        
//	        System.out.println("PUNTO 4");
	        // Datos Garantes
	        parametros.put("PnroSocioG1", ayudaPesos.getNroSocioGarante());
//	        System.out.println("Parametro PnroSocioG1: " + parametros.get("PnroSocioG1"));
	        
	        parametros.put("PnombreG1", (garantiaDTO.getNombreGarante1() != null && !garantiaDTO.getNombreGarante1().isEmpty()) 
	                ? garantiaDTO.getNombreGarante1() : " ");
//	        System.out.println("Parametro PnombreG1: " + parametros.get("PnombreG1"));
	        
	        parametros.put("PdomicilioG1", (garantiaDTO.getDireccionGarante1() != null && !garantiaDTO.getDireccionGarante1().isEmpty()) 
	                ? garantiaDTO.getDireccionGarante1() : " ");
//	        System.out.println("Parametro PdomicilioG1: " + parametros.get("PdomicilioG1"));
	        
	        parametros.put("PlocalidadG1", (garantiaDTO.getLocalidadGarante1() != null && !garantiaDTO.getLocalidadGarante1().isEmpty()) 
	                ? garantiaDTO.getLocalidadGarante1() : " ");
//	        System.out.println("Parametro PlocalidadG1: " + parametros.get("PlocalidadG1"));
	        
	     // Si se desea enviar 0 en caso de que no exista el número de socio garante 2:
	        parametros.put("PnroSocioG2", ayudaPesos.getNroSocioGarante2());
//	        System.out.println("Parametro PnroSocioG2: " + parametros.get("PnroSocioG2"));
	        
	        parametros.put("PnombreG2", (garantiaDTO.getNombreGarante2() != null && !garantiaDTO.getNombreGarante2().isEmpty()) 
	                ? garantiaDTO.getNombreGarante2() : " ");
	        
//	        System.out.println("Parametro PnombreG2: " + parametros.get("PnombreG2"));
	        
	        parametros.put("PdomicilioG2", (garantiaDTO.getDireccionGarante2() != null && !garantiaDTO.getDireccionGarante2().isEmpty()) 
	                ? garantiaDTO.getDireccionGarante2() : " ");
//	        System.out.println("Parametro PdomicilioG2: " + parametros.get("PdomicilioG2"));
	        
	        parametros.put("PlocalidadG2", (garantiaDTO.getLocalidadGarante2() != null && !garantiaDTO.getLocalidadGarante2().isEmpty()) 
	                ? garantiaDTO.getLocalidadGarante2() : " ");
//	        System.out.println("Parametro PlocalidadG2: " + parametros.get("PlocalidadG2"));
	        

	        parametros.put("PnroSocioG3", ayudaPesos.getNroSocioGarante3());
//	        System.out.println("Parametro PnroSocioG3: " + parametros.get("PnroSocioG3"));
	        
	        parametros.put("PnombreG3", (garantiaDTO.getNombreGarante3() != null && !garantiaDTO.getNombreGarante3().isEmpty()) 
	                ? garantiaDTO.getNombreGarante3() : " ");
	        
//	        System.out.println("Parametro PnombreG3: " + parametros.get("PnombreG3"));
	        
	        parametros.put("PdomicilioG3", (garantiaDTO.getDireccionGarante3() != null && !garantiaDTO.getDireccionGarante3().isEmpty()) 
	                ? garantiaDTO.getDireccionGarante3() : " ");
//	        System.out.println("Parametro PdomicilioG3: " + parametros.get("PdomicilioG3"));
	        
	        parametros.put("PlocalidadG3", (garantiaDTO.getLocalidadGarante3() != null && !garantiaDTO.getLocalidadGarante3().isEmpty()) 
	                ? garantiaDTO.getLocalidadGarante3() : " ");
//	        System.out.println("Parametro PlocalidadG3: " + parametros.get("PlocalidadG3"));
	        	        
//	        System.out.println("PUNTO 5");
	       
	        Connection connection = con.getConexion();
	       
	        if (connection == null) {
	            throw new RuntimeException("La conexión a la base de datos es nula.");
	        }
//	        System.out.println("PUNTO 6");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);
//	        System.out.println("PUNTO 7");
	        JasperViewer.viewReport(jasperPrint, false);
	    
	    } catch (Exception e) {
	    	e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al generar el/los Pagares, intente nuevamente: "+ e.getMessage());
	    } finally {
	        try {
	            con.cerrarConexion();
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error al generar el/los Pagares, intente nuevamente: " + ex.getMessage());
	        }
	    }
	}

	
	private String formatearFechaHoy() {
		
		LocalDate fechaActual = LocalDate.now();
        
        Locale locale = Locale.forLanguageTag("es-ES");

        String mes = fechaActual.getMonth().getDisplayName(TextStyle.FULL, locale).toUpperCase();

        String fechaFormateada = fechaActual.getDayOfMonth() + " de " + mes + " de " + fechaActual.getYear();
        
		return fechaFormateada;
        
	}
	
	private String formatearFecha(LocalDate fecha) {
		
        Locale locale = Locale.forLanguageTag("es-ES");

        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, locale).toUpperCase();

        String fechaFormateada = fecha.getDayOfMonth() + " de " + mes + " de " + fecha.getYear();
        
        return fechaFormateada;
        
    }
	
	private String formatearFechaDDSMMSYYYY(Date fecha) {
		
		SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
        String fechaConvertida = formatoSalida.format(fecha);
        
        return fechaConvertida;
		
	}
	
    public LocalDate convertirDateToLocalDate (Date fecha) {
    	
    	return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	
    }
	
    public static String convertirImporteEnLetras(BigDecimal numero) {
        BigDecimal parteEntera = numero.setScale(0, RoundingMode.FLOOR);
        BigDecimal parteDecimal = numero.subtract(parteEntera);
        
        int parteDecimalCentavos = parteDecimal.multiply(new BigDecimal("100")).intValue();
        
        String parteEnteraEnLetras = convertirParteEntera(parteEntera.longValue());
        
        String parteDecimalEnLetras = String.format("%02d", parteDecimalCentavos);
        
        return parteEnteraEnLetras + " CON " + parteDecimalEnLetras + "/100 CTVOS.";
    }

    public static String convertirParteEntera(long numero) {
        if (numero == 0) return "CERO";
        
        // Para números menores a 1000
        if (numero < 1000) {
            return convertirNumeroMenorQueMil(numero);
        } 
        // Para números menores a 1.000.000 (miles)
        else if (numero < 1000000) {
            long miles = numero / 1000;
            long resto = numero % 1000;
            String textoMiles;
            if (miles == 1) {
                textoMiles = "MIL";
            } else {
                textoMiles = convertirParteEntera(miles) + " MIL";
            }
            if (resto > 0) {
                return textoMiles + " " + convertirNumeroMenorQueMil(resto);
            } else {
                return textoMiles;
            }
        } 
        // Para números de 1.000.000 en adelante (millones)
        else {
            long millones = numero / 1000000;
            long resto = numero % 1000000;
            String textoMillones;
            if (millones == 1) {
                textoMillones = "UN MILLÓN";
            } else {
                textoMillones = convertirParteEntera(millones) + " MILLONES";
            }
            if (resto > 0) {
                return textoMillones + " " + convertirParteEntera(resto);
            } else {
                return textoMillones;
            }
        }
    }

    private static String convertirNumeroMenorQueMil(long numero) {
        String[] unidades = { "", "UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE" };
        String[] decenasEspeciales = { "DIEZ", "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECISÉIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE" };
        String[] decenas = { "", "", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA" };
        String[] centenas = { "", "CIEN", "DOSCIENTOS", "TRESCIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS" };

        String texto = "";
        
        // Procesa las centenas
        if (numero >= 100) {
            int c = (int)(numero / 100);
            if (numero == 100) {
                return "CIEN";
            } else {
                texto += centenas[c];
            }
            numero %= 100;
            if (numero > 0) {
                texto += " ";
            }
        }
        
        // Procesa decenas y unidades
        if (numero >= 20) {
            int d = (int)(numero / 10);
            texto += decenas[d];
            numero %= 10;
            if (numero > 0) {
                texto += " Y " + unidades[(int) numero];
            }
        } else if (numero >= 10) {
            texto += decenasEspeciales[(int)(numero - 10)];
        } else if (numero > 0) {
            texto += unidades[(int) numero];
        }
        
        return texto;
    }

	/*
    public static String convertirParteEntera(long numero) {
        if (numero == 0) return "CERO";

        String[] unidades = { "", "UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE" };
        String[] decenas = { "", "DIEZ", "ONCE", "DOCE", "TRECE", "CATORCE", "QUINCE", "DIECISÉIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE" };
        String[] decenasDecimales = { "", "", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA" };
        String[] centenas = { "", "CIEN", "DOSCIENTOS", "TRESCIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS" };
        String[] miles = { "", "MIL", "DOS MIL", "TRES MIL", "CUATRO MIL", "CINCO MIL", "SEIS MIL", "SIETE MIL", "OCHO MIL", "NUEVE MIL" };
        String[] millones = { "", "UN MILLÓN", "DOS MILLONES", "TRES MILLONES", "CUATRO MILLONES", "CINCO MILLONES", "SEIS MILLONES", "SIETE MILLONES", "OCHO MILLONES", "NUEVE MILLONES" };

        String texto = "";

        // Millones
        if (numero >= 1000000) {
            int m = (int) (numero / 1000000);
            texto += millones[m] ;
            numero %= 1000000;
            if (numero > 0) {
                texto += " ";
            }
        }

        // Miles
        if (numero >= 1000) {
            int mil = (int) (numero / 1000);
            texto += miles[mil];
            numero %= 1000;
            if (numero > 0) {
                texto += " ";
            }
        }

        // Centenas
        if (numero >= 100) {
            int c = (int) (numero / 100);
            texto += centenas[c];
            numero %= 100;
            if (numero > 0) {
                texto += " ";
            }
        }

        // Decenas y unidades
        if (numero >= 10 && numero < 20) {
            texto += decenas[(int) numero];
        } else if (numero >= 20) {
            int d = (int) (numero / 10);
            texto += decenasDecimales[d];
            numero %= 10;
            if (numero > 0) {
                texto += " Y " + unidades[(int) numero];
            }
        } else if (numero > 0) {
            texto += unidades[(int) numero];
        }

        return texto;
        
    }*/
    
    public String rellenar(String importeConvertidoALetras, int longitudTotalDeseada) {
    	
    	int necesarios = longitudTotalDeseada - importeConvertidoALetras.length();
        
        if (necesarios > 0) {
            String relleno = "*".repeat(necesarios);
            importeConvertidoALetras += relleno;
        }
        
        return importeConvertidoALetras;
    	    	
    }
    
    public String formatearBigDecimal (BigDecimal valor) {
    	
    	DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
                
        String patron = "#,##0.00";

        DecimalFormat formato = new DecimalFormat(patron, simbolos);
      
        String valorFormateado = formato.format(valor);
        
        return "$ " + valorFormateado;
    	
    }

}
