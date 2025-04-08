package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;

public interface ICobroCuotaAyudaPesosServicio {
	public CobroCuotaAyudaPesos crearCobroCuotaAyudaPesos(CobroCuotaAyudaPesos cobroCuotaAyudaPesos);
	public CobroCuotaAyudaPesos buscarCobroCuotaAyudaPesosPorId(Long id);
	public CobroCuotaAyudaPesos buscarCobroCuotaAyudaPesosPorNroAyuda(int numAyuda);
	public List<CobroCuotaAyudaPesos> buscarCobroCuotaAyudaPesosPorNumAyuda(int numAyuda);
	public boolean existeCobro(Integer nroAyuda, Integer numeroCuota);
	public void borrarCobroPorId(Long id);

}
