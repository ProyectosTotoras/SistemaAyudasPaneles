package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.Cobro;
import com.mutual.SistemaAyudaCuotas.repositorio.ICobroRepositorio;

@Service
public class CobroServicio implements ICobroServicio{

	@Autowired
	private ICobroRepositorio cobroRepositorio;

	@Override
	public List<Cobro> listarCobros() {
		return cobroRepositorio.findAll();
	}

	@Override
	public List<Cobro> listarCobroPorNumeroSocio(int numeroSocio) {
		return cobroRepositorio.findByNumeroSocio(numeroSocio);
	}

	@Override
	public Cobro buscarCobroPorNumeroSocio(int numeroSocio) {
		return cobroRepositorio.findOneByNumeroSocio(numeroSocio);
	}

	@Override
	public Cobro crearCobro(Cobro cobro) {
		return cobroRepositorio.save(cobro);
	}
	
	
}
