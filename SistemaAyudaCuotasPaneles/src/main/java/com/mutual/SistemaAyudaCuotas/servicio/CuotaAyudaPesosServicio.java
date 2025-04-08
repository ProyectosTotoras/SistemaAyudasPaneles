package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.repositorio.ICuotaAyudaPesosRepositorio;

@Service
public class CuotaAyudaPesosServicio implements ICuotaAyudaPesosServicio {
	@Autowired
	private ICuotaAyudaPesosRepositorio cuotasRepo;

	@Override
	public CuotaAyudaPesos crearCuota(CuotaAyudaPesos cuota) {
		return cuotasRepo.save(cuota);
	}

	@Override
	public List<CuotaAyudaPesos> buscarCuotasPorNumeroAyuda(Integer numeroAyuda) {
		return cuotasRepo.findByNumeroAyuda(numeroAyuda);
	}

	@Override
	public List<CuotaAyudaPesos> buscarCuotasPendientesPorNumeroAyuda(Integer numeroAyuda) {
		return cuotasRepo.findCuotasPendientes(numeroAyuda);
	}

	@Override
	public List<CuotaAyudaPesos> listarCuotas() {
		return cuotasRepo.findAll();
	}
	
	
}
