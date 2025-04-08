package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;

public interface ICuotasServicio {
	CuotaAyudaPesos crearCuota(CuotaAyudaPesos cuota);
	List<CuotaAyudaPesos> buscarCuotaPorNumeroAyuda(Integer numeroAyuda);
}
