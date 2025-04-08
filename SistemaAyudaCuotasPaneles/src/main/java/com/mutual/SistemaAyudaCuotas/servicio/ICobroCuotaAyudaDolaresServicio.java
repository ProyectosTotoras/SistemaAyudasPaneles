package com.mutual.SistemaAyudaCuotas.servicio;

import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;

public interface ICobroCuotaAyudaDolaresServicio {
	public CobroCuotaAyudaDolares crearCobroCuotaAyudaDolares(CobroCuotaAyudaDolares cobroCuotaAyudaPesos);
	public CobroCuotaAyudaDolares buscarCobroCuotaAyudaDolaresPorId(Long id);
	public boolean existeCobro(Integer nroAyuda, Integer numeroCuota);

}
