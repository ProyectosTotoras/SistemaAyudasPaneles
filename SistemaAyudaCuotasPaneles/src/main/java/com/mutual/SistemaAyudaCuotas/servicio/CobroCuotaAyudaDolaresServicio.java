package com.mutual.SistemaAyudaCuotas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.repositorio.ICobroCuotaAyudaDolaresRepositorio;
import com.mutual.SistemaAyudaCuotas.repositorio.ICobroCuotaAyudaPesosRepositorio;

@Service
public class CobroCuotaAyudaDolaresServicio implements ICobroCuotaAyudaDolaresServicio{

	@Autowired
	private ICobroCuotaAyudaDolaresRepositorio cobroCuotaAyudaDolaresRepositorio;
	
	@Override
	public CobroCuotaAyudaDolares crearCobroCuotaAyudaDolares(CobroCuotaAyudaDolares cobroCuotaAyudaDolares) {
		return cobroCuotaAyudaDolaresRepositorio.save(cobroCuotaAyudaDolares);
	}

	@Override
	public CobroCuotaAyudaDolares buscarCobroCuotaAyudaDolaresPorId(Long id) {
		return cobroCuotaAyudaDolaresRepositorio.findById(id).orElse(null);
	}

	@Override
	public boolean existeCobro(Integer nroAyuda, Integer numeroCuota) {
		return cobroCuotaAyudaDolaresRepositorio.existsByNumeroAyudaAndNumeroCuota(nroAyuda, numeroCuota);
		
	}

	

}
