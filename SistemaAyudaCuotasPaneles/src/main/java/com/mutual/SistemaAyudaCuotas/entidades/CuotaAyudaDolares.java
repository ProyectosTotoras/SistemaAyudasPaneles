package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CUOTAS_AYUDAS_DOLARES")
public class CuotaAyudaDolares {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_CUOTA_AYUDA_DOLARES")
    private Long idCuotaAyudaDolares;
	
	@Column(name = "NRO_AYUDA")
	private Integer numeroAyuda; 				// AYUDA
	
	@Column(name = "NUMERO_CUOTA")
	private Integer numeroCuota; 				// CUOTA
			
	@Column(name = "MONTO_CUOTA")
	private BigDecimal montoCuota;				// MONTO
	
	@Column(name = "FECHA_VTO_CUOTA")
	@Temporal(TemporalType.DATE)
	private Date fechaVtoCuota; 				// VTO
	
	@Column(name = "CARTA_DOCUMENTO_ENVIADA")
	private String cartaDocumentoEnviada; 		// CARTA

	public CuotaAyudaDolares() {
		
	}

	public CuotaAyudaDolares(Long idCuotaAyudaDolares, Integer numeroAyuda, Integer numeroCuota, BigDecimal montoCuota,
			Date fechaVtoCuota, String cartaDocumentoEnviada) {
		super();
		this.idCuotaAyudaDolares = idCuotaAyudaDolares;
		this.numeroAyuda = numeroAyuda;
		this.numeroCuota = numeroCuota;
		this.montoCuota = montoCuota;
		this.fechaVtoCuota = fechaVtoCuota;
		this.cartaDocumentoEnviada = cartaDocumentoEnviada;
	}

	public Long getIdCuotaAyudaDolares() {
		return idCuotaAyudaDolares;
	}

	public void setIdCuotaAyudaDolares(Long idCuotaAyudaDolares) {
		this.idCuotaAyudaDolares = idCuotaAyudaDolares;
	}

	public Integer getNumeroAyuda() {
		return numeroAyuda;
	}

	public void setNumeroAyuda(Integer numeroAyuda) {
		this.numeroAyuda = numeroAyuda;
	}

	public Integer getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public BigDecimal getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(BigDecimal montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Date getFechaVtoCuota() {
		return fechaVtoCuota;
	}

	public void setFechaVtoCuota(Date fechaVtoCuota) {
		this.fechaVtoCuota = fechaVtoCuota;
	}

	public String getCartaDocumentoEnviada() {
		return cartaDocumentoEnviada;
	}

	public void setCartaDocumentoEnviada(String cartaDocumentoEnviada) {
		this.cartaDocumentoEnviada = cartaDocumentoEnviada;
	}

}