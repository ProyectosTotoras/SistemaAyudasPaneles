package com.mutual.SistemaAyudaCuotas.servicio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Cuota;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.SaldoAyudaPorSocioDTO;
import com.mutual.SistemaAyudaCuotas.entidades.Socio;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.view.JasperViewer;

@Service
public class ListSaldosAyudasPorSocioServicio {

	private IAyudaPesosServicio ayudaPesosServicio;
	private ICuotaAyudaPesosServicio cuotaAyudaPesosServicio;
	private ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServicio;
	private ISocioServicio socioServicio;
	private final ISaldoAyudaPorSocioDTOServicio saldoAyudaPorSocioDTOServicio;

	public ListSaldosAyudasPorSocioServicio(
			IAyudaPesosServicio ayudaPesosServicio,
			ICuotaAyudaPesosServicio cuotaAyudaPesosServicio,
			ISocioServicio socioServicio,
			ICobroCuotaAyudaPesosServicio cobroCuotaAyudaPesosServici
		,ISaldoAyudaPorSocioDTOServicio saldoAyudaPorSocioDTOServicio	) {
		this.ayudaPesosServicio = ayudaPesosServicio;
		this.cuotaAyudaPesosServicio = cuotaAyudaPesosServicio;
		this.socioServicio = socioServicio;
		this.cobroCuotaAyudaPesosServicio = cobroCuotaAyudaPesosServici;
		  this.saldoAyudaPorSocioDTOServicio = saldoAyudaPorSocioDTOServicio;
	}
	
	 public void generarListado() throws JRException {
	        List<SaldoAyudaPorSocioDTO> listaSaldoAyudaPorSocioDTO = saldoAyudaPorSocioDTOServicio.findSaldoAyuda();
	       
	        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(listaSaldoAyudaPorSocioDTO.toArray());
	        Map<String, Object> parametros = new HashMap<>();
	      
	        BigDecimal totalMontos = BigDecimal.ZERO;
	        BigDecimal totalInteres = BigDecimal.ZERO;

	        totalMontos =  listaSaldoAyudaPorSocioDTO.stream(
	        		).map(SaldoAyudaPorSocioDTO::getMontoCuota)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	        
	         // 2. Sumar la columna "interes" de todas las ayudas.
	         totalInteres = listaSaldoAyudaPorSocioDTO.stream()
	             .map(SaldoAyudaPorSocioDTO::getInteres)
	             .reduce(BigDecimal.ZERO, BigDecimal::add);
	        
	         Set<Integer> sociosUnicos = listaSaldoAyudaPorSocioDTO.stream()
	                 .map(SaldoAyudaPorSocioDTO::getNumeroSocioTitular)
	                 .collect(Collectors.toSet());
	         long totalSociosUnicos = sociosUnicos.size();
	            
	         long totalAyudas = listaSaldoAyudaPorSocioDTO.size();
	         
	         long garantia1Count = listaSaldoAyudaPorSocioDTO.stream()
	            		    .filter(dto -> dto.getGarantia() == 1)
	            		    .count();

	         long garantia2Count = listaSaldoAyudaPorSocioDTO.stream()
	            		    .filter(dto -> dto.getGarantia() == 2)
	            		    .count();

	         long garantia3Count = listaSaldoAyudaPorSocioDTO.stream()
	            		    .filter(dto -> dto.getGarantia() == 3)
	            		    .count();

	       /*  System.out.println("Personal: " + garantia1Count);
	         System.out.println("Prendaria: " + garantia2Count);
	         System.out.println("Hipotecaria: " + garantia3Count);

	             // Imprimir resultados
	         System.out.println("Suma de montos: " + totalMontos);
	         System.out.println("Suma de intereses: " + totalInteres);
	         System.out.println("Número de socios únicos: " + totalSociosUnicos);
	         System.out.println("Total de ayudas: " + totalAyudas);*/
	         
	         parametros.put("ds", dataSource);
	         parametros.put("totalMontos", totalMontos);
	         parametros.put("totalInteres", totalInteres);
	         parametros.put("totalSociosUnicos", totalSociosUnicos);
	         parametros.put("totalAyudas", totalAyudas);
	         parametros.put("garantia1Count", garantia1Count);
	         parametros.put("garantia2Count", garantia2Count);
	         parametros.put("garantia3Count", garantia3Count);

	        
	        String rutaReporte = "src/main/resources/Reportes/SaldoDeAyudasPorSocioReporte.jrxml";
	 
	        JasperReport jasperReport = JasperCompileManager.compileReport(rutaReporte);
	     
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
	       
	        JasperViewer.viewReport(jasperPrint, false);
	    }
}
 