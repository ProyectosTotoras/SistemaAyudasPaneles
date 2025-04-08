package com.mutual.SistemaAyudaCuotas.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.mutual.SistemaAyudaCuotas.entidades.Cobro;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;

public class CobroCuotasReporteDto {
	  private Date legales;
	  private CobroCuotaAyudaPesos cobro;
	  private int cantidadCuotas;

	  private int nroAyuda;
	  private int nroSocio;
	  private String nombreSocio;
	  private String nombreGarante1;
	  private String nombreGarante2;
	  private String nombreGarante3;
	  
	  private BigDecimal importeCuotaAmortizacion;
	  private Date fechaTasasServiciosAl;
	  private BigDecimal importeTasasServiciosAl;
	  private BigDecimal importeCargosAdicionales;
	  
	  private BigDecimal importeHonorariosDescPagoAdel;
	  
	  private int cuotas;
	  private BigDecimal total;
	  private BigDecimal saldo;
	  
	  private String novacion;
	  
	  private BigDecimal efectivo;
	  private BigDecimal transferencia;
	  private BigDecimal totalCheques;
	  
	  private int nroCheque1;
	  private String banco1;
	  private BigDecimal cheque1;
	  private int nroCheque2;
	  private String banco2;
	  private BigDecimal cheque2;
	  private int nroCheque3;
	  private String banco3;
	  private BigDecimal cheque3;
	  
	  private int cuotaDesde;
	  private int cuotaHasta;
	  
	  BigDecimal importeMontoCuota;
	  
	public Date getLegales() {
		return legales;
	}
	public void setLegales(Date legales) {
		this.legales = legales;
	}
	
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	public int getNroAyuda() {
		return nroAyuda;
	}
	public void setNroAyuda(int nroAyuda) {
		this.nroAyuda = nroAyuda;
	}
	public int getNroSocio() {
		return nroSocio;
	}
	public void setNroSocio(int nroSocio) {
		this.nroSocio = nroSocio;
	}
	public String getNombreSocio() {
		return nombreSocio;
	}
	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}
	public String getNombreGarante1() {
		return nombreGarante1;
	}
	public void setNombreGarante1(String nombreGarante1) {
		this.nombreGarante1 = nombreGarante1;
	}
	public String getNombreGarante2() {
		return nombreGarante2;
	}
	public void setNombreGarante2(String nombreGarante2) {
		this.nombreGarante2 = nombreGarante2;
	}
	public BigDecimal getImporteCuotaAmortizacion() {
		return importeCuotaAmortizacion;
	}
	public void setImporteCuotaAmortizacion(BigDecimal importeCuotaAmortizacion) {
		this.importeCuotaAmortizacion = importeCuotaAmortizacion;
	}
	public Date getFechaTasasServiciosAl() {
		return fechaTasasServiciosAl;
	}
	public void setFechaTasasServiciosAl(Date fechaTasasServiciosAl) {
		this.fechaTasasServiciosAl = fechaTasasServiciosAl;
	}
	public BigDecimal getImporteTasasServiciosAl() {
		return importeTasasServiciosAl;
	}
	public void setImporteTasasServiciosAl(BigDecimal importeTasasServiciosAl) {
		this.importeTasasServiciosAl = importeTasasServiciosAl;
	}
	public BigDecimal getImporteCargosAdicionales() {
		return importeCargosAdicionales;
	}
	public void setImporteCargosAdicionales(BigDecimal importeCargosAdicionales) {
		this.importeCargosAdicionales = importeCargosAdicionales;
	}
	public BigDecimal getImporteHonorariosDescPagoAdel() {
		return importeHonorariosDescPagoAdel;
	}
	public void setImporteHonorariosDescPagoAdel(BigDecimal importeHonorariosDescPagoAdel) {
		this.importeHonorariosDescPagoAdel = importeHonorariosDescPagoAdel;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public String getNovacion() {
		return novacion;
	}
	public void setNovacion(String novacion) {
		this.novacion = novacion;
	}
	public BigDecimal getEfectivo() {
		return efectivo;
	}
	public void setEfectivo(BigDecimal efectivo) {
		this.efectivo = efectivo;
	}
	public BigDecimal getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(BigDecimal transferencia) {
		this.transferencia = transferencia;
	}
	public BigDecimal getTotalCheques() {
		return totalCheques;
	}
	public void setTotalCheques(BigDecimal totalCheques) {
		this.totalCheques = totalCheques;
	}
	public int getNroCheque1() {
		return nroCheque1;
	}
	public void setNroCheque1(int nroCheque1) {
		this.nroCheque1 = nroCheque1;
	}
	public String getBanco1() {
		return banco1;
	}
	public void setBanco1(String banco1) {
		this.banco1 = banco1;
	}
	public BigDecimal getCheque1() {
		return cheque1;
	}
	public void setCheque1(BigDecimal cheque1) {
		this.cheque1 = cheque1;
	}
	public int getNroCheque2() {
		return nroCheque2;
	}
	public void setNroCheque2(int nroCheque2) {
		this.nroCheque2 = nroCheque2;
	}
	public String getBanco2() {
		return banco2;
	}
	public void setBanco2(String banco2) {
		this.banco2 = banco2;
	}
	public BigDecimal getCheque2() {
		return cheque2;
	}
	public void setCheque2(BigDecimal cheque2) {
		this.cheque2 = cheque2;
	}
	public int getNroCheque3() {
		return nroCheque3;
	}
	public void setNroCheque3(int nroCheque3) {
		this.nroCheque3 = nroCheque3;
	}
	public String getBanco3() {
		return banco3;
	}
	public void setBanco3(String banco3) {
		this.banco3 = banco3;
	}
	public BigDecimal getCheque3() {
		return cheque3;
	}
	public void setCheque3(BigDecimal cheque3) {
		this.cheque3 = cheque3;
	}
	public int getCuotaDesde() {
		return cuotaDesde;
	}
	public void setCuotaDesde(int cuotaDesde) {
		this.cuotaDesde = cuotaDesde;
	}
	public int getCuotaHasta() {
		return cuotaHasta;
	}
	public void setCuotaHasta(int cuotaHasta) {
		this.cuotaHasta = cuotaHasta;
	}
	public CobroCuotaAyudaPesos getCobro() {
		return cobro;
	}
	public void setCobro(CobroCuotaAyudaPesos cobro) {
		this.cobro = cobro;
	}
	public String getNombreGarante3() {
		return nombreGarante3;
	}
	public void setNombreGarante3(String nombreGarante3) {
		this.nombreGarante3 = nombreGarante3;
	}
	public BigDecimal getImporteMontoCuota() {
		return importeMontoCuota;
	}
	public void setImporteMontoCuota(BigDecimal importeMontoCuota) {
		this.importeMontoCuota = importeMontoCuota;
	}
}

