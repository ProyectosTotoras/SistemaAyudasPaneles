package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "CUOTAS")
public class Cuota {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_CUOTA")
    private Long idCuota;
	
	@Column(name = "NUMERO_SOCIO")
	private Integer numeroSocio; 		// SOCIO
	
	@Column(name = "MES")
	private Integer mes; 				// MES
			
	@Column(name = "ANO")
	private Integer ano; 				// ANO
		
	@Column(name = "MONTO")
	private BigDecimal monto;			// MONTO

	public Cuota() {		
	}

	public Cuota(Integer numeroSocio, Integer mes, Integer ano, BigDecimal monto) {
		super();
		this.numeroSocio = numeroSocio;
		this.mes = mes;
		this.ano = ano;
		this.monto = monto;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Long getIdCuota() {
		return idCuota;
	}

	public void setIdCuota(Long idCuota) {
		this.idCuota = idCuota;
	}

}
