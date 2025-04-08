package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.repositorio.ICuotaAyudaDolaresRepositorio;
import com.mutual.SistemaAyudaCuotas.repositorio.ICuotaAyudaPesosRepositorio;

@Service
public class CuotaAyudaDolaresServicio implements ICuotaAyudaDolaresServicio {
	@Autowired
	private ICuotaAyudaDolaresRepositorio cuotasRepo;

	@Override
	public CuotaAyudaDolares crearCuota(CuotaAyudaDolares cuota) {
		return cuotasRepo.save(cuota);
	}

	@Override
	public List<CuotaAyudaDolares> buscarCuotaPorNumeroAyuda(Integer numeroAyuda) {
		return cuotasRepo.findByNumeroAyuda(numeroAyuda);
	}
	
	
}
