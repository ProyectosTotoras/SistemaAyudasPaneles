package com.mutual.SistemaAyudaCuotas.servicio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mutual.SistemaAyudaCuotas.dto.CobroCuotasReporteDto;
import com.mutual.SistemaAyudaCuotas.utilidades.Conexion;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
public class ReciboCobroCuotaAyudaPesosServicio {
	
Conexion con = new Conexion();
	
	public void generarReporte(CobroCuotasReporteDto infoParaReporte) {
		
		try {

			String rutaReporte = "src/main/resources/Reportes/ReciboPagoCuota.jrxml";
			JasperReport jasperReport;

			jasperReport = JasperCompileManager.compileReport(rutaReporte);

			// Parametros
			Map<String, Object> parametros = new HashMap<>();
			
			parametros.put("Plegales", infoParaReporte.getLegales() == null ? "S" : "N");
			
			if (infoParaReporte.getCuotaDesde() != 0 && infoParaReporte.getCuotaHasta() != 0) {
				parametros.put("Pcuota", infoParaReporte.getCuotaDesde() + "/" + infoParaReporte.getCuotaHasta());
			} else {
				parametros.put("Pcuota", String.valueOf(infoParaReporte.getCobro().getNumeroCuota()));
			}
			
			parametros.put("PcantidadCuotas", String.valueOf(infoParaReporte.getCantidadCuotas()));
			
			parametros.put("PnroAyuda", String.valueOf(infoParaReporte.getNroAyuda()));
			parametros.put("PnroSocio", String.valueOf(infoParaReporte.getNroSocio()));
			parametros.put("PnombreSocio", infoParaReporte.getNombreSocio());
			parametros.put("PnombreGarante1", infoParaReporte.getNombreGarante1() != null && !infoParaReporte.getNombreGarante1().isEmpty()
					? infoParaReporte.getNombreGarante1() : " ");
			parametros.put("PnombreGarante2", infoParaReporte.getNombreGarante2() != null && !infoParaReporte.getNombreGarante2().isEmpty()
					? infoParaReporte.getNombreGarante2() : " ");
			parametros.put("PnombreGarante3", infoParaReporte.getNombreGarante3() != null && !infoParaReporte.getNombreGarante3().isEmpty()
					? infoParaReporte.getNombreGarante3() : " ");
			
			parametros.put("PimporteCuotaAmortizacion", (infoParaReporte.getImporteCuotaAmortizacion() == null || infoParaReporte.getImporteCuotaAmortizacion().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getImporteCuotaAmortizacion()));
			parametros.put("PfechaTasasServiciosAl", formatearFechaDDSMMSYYYY(infoParaReporte.getFechaTasasServiciosAl()));	 //tra.fecha
			parametros.put("PimporteTasasServiciosAl", (infoParaReporte.getImporteTasasServiciosAl() == null || infoParaReporte.getImporteTasasServiciosAl().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getImporteTasasServiciosAl()));
			parametros.put("PimporteCargosAdicionales", (infoParaReporte.getImporteCargosAdicionales() == null || infoParaReporte.getImporteCargosAdicionales().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getImporteCargosAdicionales()));
			parametros.put("PimporteHonorariosDescPagoAdel", (infoParaReporte.getImporteHonorariosDescPagoAdel() == null || infoParaReporte.getImporteHonorariosDescPagoAdel().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getImporteHonorariosDescPagoAdel()));
			
			if (infoParaReporte.getCuotas() > 1) {
				parametros.put("PvalidaCuotaSaldo", "Cuotas");
				parametros.put("PmuestraCuotasEnSaldo", ((infoParaReporte.getImporteMontoCuota() == null || infoParaReporte.getImporteMontoCuota().compareTo(BigDecimal.ZERO) == 0)
						? "$ 0,00" : formatearBigDecimal(infoParaReporte.getImporteMontoCuota())) + " X " + 
						infoParaReporte.getCuotas() + " CUOTAS      ");
			} else {
				parametros.put("PvalidaCuotaSaldo", "1Cuota");
				parametros.put("PmuestraCuotasEnSaldo", " CUOTA             ");
			}
			
			parametros.put("Ptotal", (infoParaReporte.getTotal() == null || infoParaReporte.getTotal().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getTotal()));
			
			parametros.put("Pnovacion", (infoParaReporte.getNovacion() == null || infoParaReporte.getNovacion().isEmpty()) 
					? "N" : infoParaReporte.getNovacion());
			
			parametros.put("Pefectivo", (infoParaReporte.getEfectivo() == null || infoParaReporte.getEfectivo().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getEfectivo()));
			parametros.put("Ptransferencia", (infoParaReporte.getTransferencia() == null || infoParaReporte.getTransferencia().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getTransferencia()));
			parametros.put("Pcheques", formatearBigDecimal(infoParaReporte.getTotalCheques()));
			
			parametros.put("PnroCheque1", infoParaReporte.getNroCheque1() == 0 ? "0" : String.valueOf(infoParaReporte.getNroCheque1()));
			parametros.put("PnroCheque2", infoParaReporte.getNroCheque2() == 0 ? "0" : String.valueOf(infoParaReporte.getNroCheque2()));
			parametros.put("PnroCheque3", infoParaReporte.getNroCheque3() == 0 ? "0" : String.valueOf(infoParaReporte.getNroCheque3()));
			
			parametros.put("Pbanco1", (infoParaReporte.getBanco1() == null || infoParaReporte.getBanco1().isEmpty()) ? " " : infoParaReporte.getBanco1());
			parametros.put("Pbanco2", (infoParaReporte.getBanco2() == null || infoParaReporte.getBanco2().isEmpty()) ? " " : infoParaReporte.getBanco2());
			parametros.put("Pbanco3", (infoParaReporte.getBanco3() == null || infoParaReporte.getBanco3().isEmpty()) ? " " : infoParaReporte.getBanco3());
			
			parametros.put("Pcheque1", (infoParaReporte.getCheque1() == null || infoParaReporte.getCheque1().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getCheque1()));
			parametros.put("Pcheque2", (infoParaReporte.getCheque2() == null || infoParaReporte.getCheque2().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getCheque2()));
			parametros.put("Pcheque3", (infoParaReporte.getCheque3() == null || infoParaReporte.getCheque3().compareTo(BigDecimal.ZERO) == 0)
					? "$ 0,00" : formatearBigDecimal(infoParaReporte.getCheque3()));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.getConexion());
			
			// Mostrar el reporte en el JasperViewer
			JasperViewer.viewReport(jasperPrint, false);
			

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al generar el Recibo de Pago, intente nuevamente");
			//e.printStackTrace();
		} 
		finally {

			try {

				con.cerrarConexion();
				
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al generar el Recibo de Pago, intente nuevamente");
			}
			
		}
		
	}
	
	private String formatearFechaDDSMMSYYYY(Date fecha) {
		
		SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");
        String fechaConvertida = formatoSalida.format(fecha);
        
        return fechaConvertida;
		
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