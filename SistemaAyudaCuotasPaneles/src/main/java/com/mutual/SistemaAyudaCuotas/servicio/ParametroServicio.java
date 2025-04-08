package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.Parametro;
import com.mutual.SistemaAyudaCuotas.repositorio.IParemetroRepositorio;

@Service
public class ParametroServicio implements IParametroServicio{

	@Autowired
	private IParemetroRepositorio parametroRepo;
	
	@Override
	public List<Parametro> listarParametros() {
		return parametroRepo.findAll();
	}

	@Override
	public Parametro traerParametro(Long id) {
		return parametroRepo.findById(id).orElse(null);
	}

}
