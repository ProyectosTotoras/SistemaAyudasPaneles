package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GARANTIA")
public class Garantia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "CODIGO")
	private Integer codigo;
	
	@Column(name = "GARANTIA")
	private String garantiaNom;
	
	@Column(name = "PORCENTAJE")
	private BigDecimal porcentaje;						//PORCE

	public Garantia() {
		
	}

	public Garantia(Integer codigo, String garantiaNom, BigDecimal porcentaje) {
		super();
		this.codigo = codigo;
		this.garantiaNom = garantiaNom;
		this.porcentaje = porcentaje;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getGarantiaNom() {
		return this.garantiaNom;
	}

	public void setGarantiaNom(String garantiaNom) {
		this.garantiaNom = garantiaNom;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	@Override
	public String toString() {
		return this.garantiaNom;
	}
}


