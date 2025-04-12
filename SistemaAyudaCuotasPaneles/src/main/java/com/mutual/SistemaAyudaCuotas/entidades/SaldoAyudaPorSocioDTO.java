package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;
import java.util.Date;

public class SaldoAyudaPorSocioDTO {

	private int numeroSocioTitular;
	private String nombreSocioTitular;
	private int numeroAyuda;
	private int garantia;
	private Date fechaAltaAyuda;
	private String destinoAyuda;
	private BigDecimal interes;
	private BigDecimal montoCuota;
	private int cuotas;
	public SaldoAyudaPorSocioDTO(int numeroSocioTitular,
            String nombreSocioTitular,
            int numeroAyuda,
            Date fechaAltaAyuda,
            String destinoAyuda,
            BigDecimal interes,
            int garantia,
            BigDecimal montoCuota,
            int cantidadCuotas) {
this.numeroSocioTitular = numeroSocioTitular;
this.nombreSocioTitular = nombreSocioTitular;
this.numeroAyuda = numeroAyuda;
this.fechaAltaAyuda = fechaAltaAyuda;
this.destinoAyuda = destinoAyuda;
this.interes = interes;
this.garantia = garantia;
this.montoCuota = montoCuota;
this.cuotas = cantidadCuotas;
}



	public int getGarantia() {
		return garantia;
	}
	public void setGarantia(int garantia) {
		this.garantia = garantia;
	}
	public int getNumeroSocioTitular() {
		return numeroSocioTitular;
	}
	public void setNumeroSocioTitular(int numeroSocioTitular) {
		this.numeroSocioTitular = numeroSocioTitular;
	}
	public String getNombreSocioTitular() {
		return nombreSocioTitular;
	}
	public void setNombreSocioTitular(String nombreSocioTitular) {
		this.nombreSocioTitular = nombreSocioTitular;
	}
	public int getNumeroAyuda() {
		return numeroAyuda;
	}
	public void setNumeroAyuda(int numeroAyuda) {
		this.numeroAyuda = numeroAyuda;
	}
	public Date getFechaAltaAyuda() {
		return fechaAltaAyuda;
	}
	public void setFechaAltaAyuda(Date fechaAltaAyuda) {
		this.fechaAltaAyuda = fechaAltaAyuda;
	}
	public String getDestinoAyuda() {
		return destinoAyuda;
	}
	public void setDestinoAyuda(String destinoAyuda) {
		this.destinoAyuda = destinoAyuda;
	}
	public BigDecimal getInteres() {
		return interes;
	}
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
	public BigDecimal getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(BigDecimal montoCuota) {
		this.montoCuota = montoCuota;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

}
