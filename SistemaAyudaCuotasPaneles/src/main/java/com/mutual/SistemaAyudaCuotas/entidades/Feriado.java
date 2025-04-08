package com.mutual.SistemaAyudaCuotas.entidades;

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
@Table(name = "FERIADOS")
public class Feriado {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_FERIADO")
    private Long idFeriado;
	
	@Column(name = "FECHA")
	@Temporal(TemporalType.DATE)
	private Date fecha; 				// FECHA
	
	public Feriado() {		
	}

	public Feriado(Date fecha) {
		super();
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdFeriado() {
		return idFeriado;
	}

	public void setIdFeriado(Long idFeriado) {
		this.idFeriado = idFeriado;
	}

}
