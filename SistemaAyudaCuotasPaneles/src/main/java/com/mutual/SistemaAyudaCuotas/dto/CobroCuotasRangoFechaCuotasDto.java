package com.mutual.SistemaAyudaCuotas.dto;

import java.sql.Date;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;

public class CobroCuotasRangoFechaCuotasDto {
	
	private int desde;
	private int hasta;
	
	
	public int getDesde() {
		return desde;
	}
	public void setDesde(int desde) {
		this.desde = desde;
	}
	public int getHasta() {
		return hasta;
	}
	public void setHasta(int hasta) {
		this.hasta = hasta;
	}
	
	
}
