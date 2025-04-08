package com.mutual.SistemaAyudaCuotas.servicio;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mutual.SistemaAyudaCuotas.utilidades.Conexion;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReciboCuotaSocietariaServicio {
	
Conexion con = new Conexion();
	
	public void generarReporte(Integer nroSocio, Integer nroSocioG1, Integer nroSocioG2, Integer nroSocioG3, 
			String nombreSocio, String nombreSocioG1, String nombreSocioG2, String nombreSocioG3, 
			String periodoDesde, String periodoHasta,
			String periodoDesdeG1, String periodoHastaG1, String periodoDesdeG2, String periodoHastaG2, String periodoDesdeG3, String periodoHastaG3,
			BigDecimal deudaSocio, BigDecimal deudaSocioG1, BigDecimal deudaSocioG2, BigDecimal deudaSocioG3,
			BigDecimal totalACobrar) {
		
		try {

			String rutaReporte = "src/main/resources/Reportes/ReciboPagoCuotaSocietaria.jrxml";
			JasperReport jasperReport;

			jasperReport = JasperCompileManager.compileReport(rutaReporte);

			// Parametros
			Map<String, Object> parametros = new HashMap<>();
						
			parametros.put("PnroSocio", (nroSocio != null && nroSocio != 0) ? String.valueOf(nroSocio) : "");
			parametros.put("PnroSocioGarante1", (nroSocioG1 != null && nroSocioG1 != 0) ? String.valueOf(nroSocioG1) : "");
			parametros.put("PnroSocioGarante2", (nroSocioG2 != null && nroSocioG2 != 0) ? String.valueOf(nroSocioG2) : "");
			parametros.put("PnroSocioGarante3", (nroSocioG3 != null && nroSocioG3 != 0) ? String.valueOf(nroSocioG3) : "");
			
			parametros.put("PnombreSocio", (nombreSocio != null && !nombreSocio.isEmpty()) ? nombreSocio : "");
			parametros.put("PnombreGarante1", (nombreSocioG1 != null && !nombreSocioG1.isEmpty()) ? nombreSocioG1 : "");
			parametros.put("PnombreGarante2", (nombreSocioG2 != null && !nombreSocioG2.isEmpty()) ? nombreSocioG2 : "");
			parametros.put("PnombreGarante3", (nombreSocioG3 != null && !nombreSocioG3.isEmpty()) ? nombreSocioG3 : "");
			
			parametros.put("PperiodoDesde", (periodoDesde != null && !periodoDesde.isEmpty()) ? periodoDesde : "");
			parametros.put("PperiodoHasta", (periodoHasta != null && !periodoHasta.isEmpty()) ? periodoHasta : "");
			parametros.put("PperiodoDesdeGarante1", (periodoDesdeG1 != null && !periodoDesdeG1.isEmpty()) ? periodoDesdeG1 : "");
			parametros.put("PperiodoHastaGarante1", (periodoHastaG1 != null && !periodoHastaG1.isEmpty()) ? periodoHastaG1 : "");
			parametros.put("PperiodoDesdeGarante2", (periodoDesdeG2 != null && !periodoDesdeG2.isEmpty()) ? periodoDesdeG2 : "");
			parametros.put("PperiodoHastaGarante2", (periodoHastaG2 != null && !periodoHastaG2.isEmpty()) ? periodoHastaG2 : "");
			parametros.put("PperiodoDesdeGarante3", (periodoDesdeG3 != null && !periodoDesdeG3.isEmpty()) ? periodoDesdeG3 : "");
			parametros.put("PperiodoHastaGarante3", (periodoHastaG3 != null && !periodoHastaG3.isEmpty()) ? periodoHastaG3 : "");
			
			parametros.put("PdeudaSocio", (deudaSocio != null && deudaSocio.compareTo(BigDecimal.ZERO) != 0) ? formatearBigDecimal(deudaSocio) : "");
			parametros.put("PdeudaSocioGarante1", (deudaSocioG1 != null && deudaSocioG1.compareTo(BigDecimal.ZERO) != 0) ? formatearBigDecimal(deudaSocioG1) : "");
			parametros.put("PdeudaSocioGarante2", (deudaSocioG2 != null && deudaSocioG2.compareTo(BigDecimal.ZERO) != 0) ? formatearBigDecimal(deudaSocioG2) : "");
			parametros.put("PdeudaSocioGarante3", (deudaSocioG3 != null && deudaSocioG3.compareTo(BigDecimal.ZERO) != 0) ? formatearBigDecimal(deudaSocioG3) : "");
			
			parametros.put("PtotalACobrar", (totalACobrar != null && totalACobrar.compareTo(BigDecimal.ZERO) != 0) ? formatearBigDecimal(totalACobrar) : "");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.getConexion());
			
			// Mostrar el reporte en el JasperViewer
			JasperViewer.viewReport(jasperPrint, false);
			

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al generar el Recibo de Pago Cuota Societaria");
 			//e.printStackTrace();
		} 
		finally {

			try {

				con.cerrarConexion();
				
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al generar el Recibo de Pago Cuota Societaria");	}
			
		}
		
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