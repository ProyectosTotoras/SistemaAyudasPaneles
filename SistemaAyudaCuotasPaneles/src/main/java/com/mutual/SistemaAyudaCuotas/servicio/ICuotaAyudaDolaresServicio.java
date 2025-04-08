package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaDolares;

public interface ICuotaAyudaDolaresServicio {
	CuotaAyudaDolares crearCuota(CuotaAyudaDolares cuota);
	List<CuotaAyudaDolares> buscarCuotaPorNumeroAyuda(Integer numeroAyuda);
}
