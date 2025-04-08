package com.mutual.SistemaAyudaCuotas.dto;

import java.util.Date;

import com.mutual.SistemaAyudaCuotas.entidades.Socio;

public class GarantiaInfoDTO {
	private String numeroGarante1;
	private String numeroGarante2;
	private String numeroGarante3;
	private String numeroSocio;
	private String nombreGarante1;
	private String nombreGarante2;
	private String nombreGarante3;
	private String nombreSocio;
	private String numeroGarantia;
	private String tipoGarantia;
	private String saldoComun;
	private String direccionSocio;
	private String localidadSocio;
	private String direccionGarante1;
	private String localidadGarante1;
	private String direccionGarante2;
	private String localidadGarante2;
	private String direccionGarante3;
	private String localidadGarante3;
	private Date fechaVencimientoSocio;
	private Date fechaVencimientoGarante1;
	private Date fechaVencimientoGarante2;
	private Date fechaVencimientoGarante3;
	
	public GarantiaInfoDTO() {
	}
	
	public GarantiaInfoDTO(String numeroGarante1, String numeroGarante2, String numeroGarante3, String numeroSocio,
			String nombreGarante1, String nombreGarante2, String nombreGarante3, String nombreSocio,
			String numeroGarantia, String tipoGarantia, String saldoComun, String direccionSocio, String localidadSocio,
			String direccionGarante1, String localidadGarante1, String direccionGarante2, String localidadGarante2,
			String direccionGarante3, String localidadGarante3,	Date fechaVencimientoSocio,
			Date fechaVencimientoGarante1,
			Date fechaVencimientoGarante2,
			Date fechaVencimientoGarante3) {
		super();
		this.numeroGarante1 = numeroGarante1;
		this.numeroGarante2 = numeroGarante2;
		this.numeroGarante3 = numeroGarante3;
		this.numeroSocio = numeroSocio;
		this.nombreGarante1 = nombreGarante1;
		this.nombreGarante2 = nombreGarante2;
		this.nombreGarante3 = nombreGarante3;
		this.nombreSocio = nombreSocio;
		this.numeroGarantia = numeroGarantia;
		this.tipoGarantia = tipoGarantia;
		this.saldoComun = saldoComun;
		this.direccionSocio = direccionSocio;
		this.localidadSocio = localidadSocio;
		this.direccionGarante1 = direccionGarante1;
		this.localidadGarante1 = localidadGarante1;
		this.direccionGarante2 = direccionGarante2;
		this.localidadGarante2 = localidadGarante2;
		this.direccionGarante3 = direccionGarante3;
		this.localidadGarante3 = localidadGarante3;
		this.fechaVencimientoSocio = fechaVencimientoSocio;
		this.fechaVencimientoGarante1 = fechaVencimientoGarante1;
		this.fechaVencimientoGarante2 = fechaVencimientoGarante2;
		this.fechaVencimientoGarante3 = fechaVencimientoGarante3;
	}


	public String getNumeroGarante1() {
		return numeroGarante1;
	}
	
	public void setNumeroGarante1(String numeroGarante1) {
		this.numeroGarante1 = numeroGarante1;
	}
	
	public String getNumeroGarante2() {
		return numeroGarante2;
	}
	
	public void setNumeroGarante2(String numeroGarante2) {
		this.numeroGarante2 = numeroGarante2;
	}
	
	public String getNumeroGarante3() {
		return numeroGarante3;
	}
	
	public void setNumeroGarante3(String numeroGarante3) {
		this.numeroGarante3 = numeroGarante3;
	}
	
	public String getNumeroGarantia() {
		return numeroGarantia;
	}
	
	public void setNumeroGarantia(String numeroGarantia) {
		this.numeroGarantia = numeroGarantia;
	}
	
	public String getTipoGarantia() {
		return tipoGarantia;
	}
	
	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}
	
	public String getSaldoComun() {
		return saldoComun;
	}
	
	public void setSaldoComun(String saldoComun) {
		this.saldoComun = saldoComun;
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
	
	public String getNombreGarante3() {
		return nombreGarante3;
	}
	
	public void setNombreGarante3(String nombreGarante3) {
		this.nombreGarante3 = nombreGarante3;
	}
	
	public String getNumeroSocio() {
		return numeroSocio;
	}
	
	public void setNumeroSocio(String numeroSocio) {
		this.numeroSocio = numeroSocio;
	}
	
	public String getNombreSocio() {
		return nombreSocio;
	}
	
	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}

	public String getDireccionSocio() {
		return direccionSocio;
	}

	public void setDireccionSocio(String direccionSocio) {
		this.direccionSocio = direccionSocio;
	}

	public String getLocalidadSocio() {
		return localidadSocio;
	}

	public void setLocalidadSocio(String localidadSocio) {
		this.localidadSocio = localidadSocio;
	}

	public String getDireccionGarante1() {
		return direccionGarante1;
	}

	public void setDireccionGarante1(String direccionGarante1) {
		this.direccionGarante1 = direccionGarante1;
	}

	public String getLocalidadGarante1() {
		return localidadGarante1;
	}

	public void setLocalidadGarante1(String localidadGarante1) {
		this.localidadGarante1 = localidadGarante1;
	}

	public String getDireccionGarante2() {
		return direccionGarante2;
	}

	public void setDireccionGarante2(String direccionGarante2) {
		this.direccionGarante2 = direccionGarante2;
	}

	public String getLocalidadGarante2() {
		return localidadGarante2;
	}

	public void setLocalidadGarante2(String localidadGarante2) {
		this.localidadGarante2 = localidadGarante2;
	}
	
	public String getDireccionGarante3() {
		return direccionGarante3;
	}

	public void setDireccionGarante3(String direccionGarante3) {
		this.direccionGarante3 = direccionGarante3;
	}

	public String getLocalidadGarante3() {
		return localidadGarante3;
	}

	public void setLocalidadGarante3(String localidadGarante3) {
		this.localidadGarante3 = localidadGarante3;
	}
	public Date getFechaVencimientoSocio() {
		return fechaVencimientoSocio;
	}

	public void setFechaVencimientoSocio(Date fechaVencimientoSocio) {
		this.fechaVencimientoSocio = fechaVencimientoSocio;
	}

	public Date getFechaVencimientoGarante1() {
		return fechaVencimientoGarante1;
	}

	public void setFechaVencimientoGarante1(Date fechaVencimientoGarante1) {
		this.fechaVencimientoGarante1 = fechaVencimientoGarante1;
	}

	public Date getFechaVencimientoGarante2() {
		return fechaVencimientoGarante2;
	}

	public void setFechaVencimientoGarante2(Date fechaVencimientoGarante2) {
		this.fechaVencimientoGarante2 = fechaVencimientoGarante2;
	}

	public Date getFechaVencimientoGarante3() {
		return fechaVencimientoGarante3;
	}

	public void setFechaVencimientoGarante3(Date fechaVencimientoGarante3) {
		this.fechaVencimientoGarante3 = fechaVencimientoGarante3;
	}
	
}
