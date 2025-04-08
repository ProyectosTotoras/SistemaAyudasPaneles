package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.Cuotas.Cuota;
import com.mutual.SistemaAyudaCuotas.repositorio.ICuotasRepositorio;

@Service
public class CuotasServicio implements ICuotasServicio {
	@Autowired
	private ICuotasRepositorio cuotasRepo;

	@Override
	public CuotaAyudaPesos crearCuota(CuotaAyudaPesos cuota) {
		return cuotasRepo.save(cuota);
	}

	@Override
	public List<CuotaAyudaPesos> buscarCuotaPorNumeroAyuda(Integer numeroAyuda) {
		return cuotasRepo.findByNumeroAyuda(numeroAyuda);
	}
	
	
}
