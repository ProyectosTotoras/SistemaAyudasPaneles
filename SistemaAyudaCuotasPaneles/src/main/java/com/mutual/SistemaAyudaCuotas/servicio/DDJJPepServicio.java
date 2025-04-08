package com.mutual.SistemaAyudaCuotas.servicio;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mutual.SistemaAyudaCuotas.utilidades.Conexion;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class DDJJPepServicio {
	
	Conexion con = new Conexion();
	
	public void generarReporte(Integer nroSocio, Integer nroSocioG1, Integer nroSocioG2, Integer nroSocioG3) {
		
		try {

			String rutaReporte = "src/main/resources/Reportes/DDJJ_PEP.jrxml";
			JasperReport jasperReport;

			jasperReport = JasperCompileManager.compileReport(rutaReporte);

			// Parametros
			Map<String, Object> parametros = new HashMap<>();
						
			parametros.put("PnroSocio", (nroSocio != null && nroSocio != 0) ? nroSocio : null);
			parametros.put("PnroSocioG1", (nroSocioG1 != null && nroSocioG1 != 0) ? nroSocioG1 : null);
			parametros.put("PnroSocioG2", (nroSocioG2 != null && nroSocioG2 != 0) ? nroSocioG2 : null);
			parametros.put("PnroSocioG3", (nroSocioG3 != null && nroSocioG3 != 0) ? nroSocioG3 : null);
						
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.getConexion());
			
			// Mostrar el reporte en el JasperViewer
			JasperViewer.viewReport(jasperPrint, false);
			

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al generar la DDJJ PEP");
			//e.printStackTrace();
		} 
		finally {

			try {

				con.cerrarConexion();
				
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al generar la DDJJ PEP");
			}
			
		}
		
	}

}
