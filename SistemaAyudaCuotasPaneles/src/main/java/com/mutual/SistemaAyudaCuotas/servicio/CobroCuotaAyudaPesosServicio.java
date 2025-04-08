package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.repositorio.ICobroCuotaAyudaPesosRepositorio;

@Service
public class CobroCuotaAyudaPesosServicio implements ICobroCuotaAyudaPesosServicio{

	@Autowired
	private ICobroCuotaAyudaPesosRepositorio cobroCuotaAyudaPesosRepositorio;
	
	@Override
	public CobroCuotaAyudaPesos crearCobroCuotaAyudaPesos(CobroCuotaAyudaPesos cobroCuotaAyudaPesos) {
		return cobroCuotaAyudaPesosRepositorio.save(cobroCuotaAyudaPesos);
	}

	@Override
	public CobroCuotaAyudaPesos buscarCobroCuotaAyudaPesosPorId(Long id) {
		return cobroCuotaAyudaPesosRepositorio.findById(id).orElse(null);
	}

	@Override
	public boolean existeCobro(Integer nroAyuda, Integer numeroCuota) {
		return cobroCuotaAyudaPesosRepositorio.existsByNumeroAyudaAndNumeroCuota(nroAyuda, numeroCuota);
		
	}

	@Override
	public List<CobroCuotaAyudaPesos> buscarCobroCuotaAyudaPesosPorNumAyuda(int numAyuda) {
		return cobroCuotaAyudaPesosRepositorio.findByNumeroAyuda(numAyuda);
	}

	@Override
	public void borrarCobroPorId(Long id) {
		cobroCuotaAyudaPesosRepositorio.deleteById(id);
		
	}

	@Override
	public CobroCuotaAyudaPesos buscarCobroCuotaAyudaPesosPorNroAyuda(int numAyuda) {
		return cobroCuotaAyudaPesosRepositorio.findFirstByNumeroAyuda(numAyuda);
	}

	

}
