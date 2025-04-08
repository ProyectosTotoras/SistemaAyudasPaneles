package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PARAMETRO")
public class Parametro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_PARAMETRO")
    private Long idParametro;
	
	@Column(name = "N1")
	private BigDecimal n1;
	
	@Column(name = "N2")
	private BigDecimal n2;
	
	@Column(name = "N3")
	private BigDecimal n3;
	
	@Column(name = "N4")
	private BigDecimal n4;
	
	@Column(name = "SELLCREPE1")
	private BigDecimal sellcrepe1;
	
	@Column(name = "SELLCREPE2")
	private BigDecimal sellcrepe2;
	
	@Column(name = "N5")
	private BigDecimal n5;
	
	@Column(name = "N6")
	private BigDecimal n6;
	
	@Column(name = "N7")
	private BigDecimal n7;
	
	@Column(name = "N8")
	private BigDecimal n8;
	
	@Column(name = "N9")
	private BigDecimal n9;
	
	@Column(name = "MINIMO")
	private BigDecimal minimo;
	
	@Column(name = "POR_ADMINI")
	private BigDecimal porAdmini;
	
	@Column(name = "POR_PROEDI")
	private BigDecimal porProedi;

	public Parametro() {
		
	}

	public Parametro(Long idParametro, BigDecimal n1, BigDecimal n2, BigDecimal n3, BigDecimal n4,
			BigDecimal sellcrepe1, BigDecimal sellcrepe2, BigDecimal n5, BigDecimal n6, BigDecimal n7, BigDecimal n8,
			BigDecimal n9, BigDecimal minimo, BigDecimal porAdmini, BigDecimal porProedi) {
		super();
		this.idParametro = idParametro;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		this.n4 = n4;
		this.sellcrepe1 = sellcrepe1;
		this.sellcrepe2 = sellcrepe2;
		this.n5 = n5;
		this.n6 = n6;
		this.n7 = n7;
		this.n8 = n8;
		this.n9 = n9;
		this.minimo = minimo;
		this.porAdmini = porAdmini;
		this.porProedi = porProedi;
	}

	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public BigDecimal getN1() {
		return n1;
	}

	public void setN1(BigDecimal n1) {
		this.n1 = n1;
	}

	public BigDecimal getN2() {
		return n2;
	}

	public void setN2(BigDecimal n2) {
		this.n2 = n2;
	}

	public BigDecimal getN3() {
		return n3;
	}

	public void setN3(BigDecimal n3) {
		this.n3 = n3;
	}

	public BigDecimal getN4() {
		return n4;
	}

	public void setN4(BigDecimal n4) {
		this.n4 = n4;
	}

	public BigDecimal getSellcrepe1() {
		return sellcrepe1;
	}

	public void setSellcrepe1(BigDecimal sellcrepe1) {
		this.sellcrepe1 = sellcrepe1;
	}

	public BigDecimal getSellcrepe2() {
		return sellcrepe2;
	}

	public void setSellcrepe2(BigDecimal sellcrepe2) {
		this.sellcrepe2 = sellcrepe2;
	}

	public BigDecimal getN5() {
		return n5;
	}

	public void setN5(BigDecimal n5) {
		this.n5 = n5;
	}

	public BigDecimal getN6() {
		return n6;
	}

	public void setN6(BigDecimal n6) {
		this.n6 = n6;
	}

	public BigDecimal getN7() {
		return n7;
	}

	public void setN7(BigDecimal n7) {
		this.n7 = n7;
	}

	public BigDecimal getN8() {
		return n8;
	}

	public void setN8(BigDecimal n8) {
		this.n8 = n8;
	}

	public BigDecimal getN9() {
		return n9;
	}

	public void setN9(BigDecimal n9) {
		this.n9 = n9;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}

	public BigDecimal getPorAdmini() {
		return porAdmini;
	}

	public void setPorAdmini(BigDecimal porAdmini) {
		this.porAdmini = porAdmini;
	}

	public BigDecimal getPorProedi() {
		return porProedi;
	}

	public void setPorProedi(BigDecimal porProedi) {
		this.porProedi = porProedi;
	}

}
