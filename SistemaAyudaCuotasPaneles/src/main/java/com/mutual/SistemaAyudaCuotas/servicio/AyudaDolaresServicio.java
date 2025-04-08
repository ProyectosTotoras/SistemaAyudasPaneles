package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.repositorio.IAyudaDolaresRepositorio;
import com.mutual.SistemaAyudaCuotas.repositorio.IAyudaPesosRepositorio;

@Service
public class AyudaDolaresServicio implements IAyudaDolaresServicio{
	@Autowired
	private IAyudaDolaresRepositorio ayudaRepositorio;

	@Override
	@Transactional
	public void crearAyuda(AyudaDolares ayuda) {
		ayudaRepositorio.save(ayuda);
		
	}

	@Override
	public AyudaDolares buscarAyudaPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AyudaDolares buscarAyudaPorNumeroAyuda(int id) {
		return ayudaRepositorio.findOneByNroAyuda(id).orElse(null);
	}

	@Override
	public List<AyudaDolares> listarAyudas() {
		return ayudaRepositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AyudaDolares> findTopByOrderByNumeroAyudaDesc() {
	
		return ayudaRepositorio.findTopByOrderByNroAyudaDesc();
	}

	@Override
	public List<AyudaDolares> buscarAyudasPorNumeroAyuda(Integer nroAyuda) {
		return ayudaRepositorio.findAllByNroAyuda(nroAyuda);
	}

	@Override
	public List<AyudaDolares> buscarAyudasPorNumeroSocio(Integer numeroSocio) {
		return ayudaRepositorio.findByNumeroSocio(numeroSocio);
	}

	@Override
	public List<AyudaDolares> buscarPorNumSocioNumAyuda(Integer numeroSocio, Integer nroAyuda) {
		return ayudaRepositorio.findByNumeroSocioAndNroAyuda(nroAyuda, numeroSocio);
	}

	@Override
	public void eliminarAyuda(Long idAyudaPesos) {
		ayudaRepositorio.deleteById(idAyudaPesos);
		
	}
	
	
}
