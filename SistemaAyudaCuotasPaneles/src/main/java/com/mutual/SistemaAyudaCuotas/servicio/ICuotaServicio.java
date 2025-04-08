package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.Cuota;

public interface ICuotaServicio {
	List<Cuota> buscarCuotasPorNumeroSocio(Integer numeroSocio);
	List<Cuota> buscarCuotasPorNumeroSocioHastaMesActual(Integer numeroSocio);
	void borrarCuota(Cuota cuota);
}
