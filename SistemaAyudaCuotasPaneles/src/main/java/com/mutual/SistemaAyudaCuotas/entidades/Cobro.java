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
@Table(name = "COBROS")
public class Cobro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_COBRO")
    private Long idCobro;
	
	@Column(name = "NUMERO_SOCIO")
	private Integer numeroSocio; 		// SOCIO
	
	@Column(name = "MES")
	private Integer mes; 				// MES
			
	@Column(name = "ANO")
	private Integer ano; 				// ANO
	
	@Column(name = "FECHA")
	@Temporal(TemporalType.DATE)
	private Date fecha; 				// FECHA
		
	@Column(name = "MONTO")
	private BigDecimal monto;			// MONTO
	
	@Column(name = "CONDONA")
	private String condona; 			// CONDONA

	public Cobro() {		
	}
	
	public Cobro(Integer numeroSocio, Integer mes, Integer ano, Date fecha, BigDecimal monto, String condona) {
		super();
		this.numeroSocio = numeroSocio;
		this.mes = mes;
		this.ano = ano;
		this.fecha = fecha;
		this.monto = monto;
		this.condona = condona;
	}

	public Integer getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(Integer numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getCondona() {
		return condona;
	}

	public void setCondona(String condona) {
		this.condona = condona;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

}
