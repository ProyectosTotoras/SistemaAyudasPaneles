package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;

public interface ICuotaAyudaPesosServicio {
	List<CuotaAyudaPesos> listarCuotas();
	CuotaAyudaPesos crearCuota(CuotaAyudaPesos cuota);
	List<CuotaAyudaPesos> buscarCuotasPorNumeroAyuda(Integer numeroAyuda);
	List<CuotaAyudaPesos> buscarCuotasPendientesPorNumeroAyuda(Integer numeroAyuda);
}
