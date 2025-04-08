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
@Table(name = "MOV_CAJA_AHORRO_COMUN")
public class MovimientoCajaAhorroComun {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_MOV_CAJA_AH_COMUN")
    private Long idMovCajaAhorroComun;
	
	@Column(name = "NUMERO_SOCIO")
	private Integer numeroSocio; 		// SOCIO
	
	@Column(name = "ORDEN")
	private Integer orden; 				// ORDEN
			
	@Column(name = "FECHA")
	@Temporal(TemporalType.DATE)
	private Date fecha; 				// FECHA
	
	@Column(name = "CODIGO")
	private Integer codigo;				// CODIGO
	
	@Column(name = "MONTO")
	private BigDecimal monto;			// MONTO
	
	@Column(name = "ANULADO")
	private String anulado; 			// ANULADO
		
	@Column(name = "FECHA_ACRE")
	@Temporal(TemporalType.DATE)
	private Date fechaAcre; 			// FEC_ACRE
	
	@Column(name = "CONCEPTO")
	private String concepto; 			// CONCEPTO

	public MovimientoCajaAhorroComun() {		
	}

	public MovimientoCajaAhorroComun(Integer numeroSocio, Integer orden, Date fecha, Integer codigo, BigDecimal monto,
			String anulado, Date fechaAcre, String concepto) {
		super();
		this.numeroSocio = numeroSocio;
		this.orden = orden;
		this.fecha = fecha;
		this.codigo = codigo;
		this.monto = monto;
		this.anulado = anulado;
		this.fechaAcre = fechaAcre;
		this.concepto = concepto;
	}

	public Integer getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(Integer numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getAnulado() {
		return anulado;
	}

	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}

	public Date getFechaAcre() {
		return fechaAcre;
	}

	public void setFechaAcre(Date fechaAcre) {
		this.fechaAcre = fechaAcre;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Long getIdMovCajaAhorroComun() {
		return idMovCajaAhorroComun;
	}

	public void setIdMovCajaAhorroComun(Long idMovCajaAhorroComun) {
		this.idMovCajaAhorroComun = idMovCajaAhorroComun;
	}

}
