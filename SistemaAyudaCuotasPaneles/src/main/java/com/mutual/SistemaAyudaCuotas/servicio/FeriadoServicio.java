package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.Feriado;
import com.mutual.SistemaAyudaCuotas.repositorio.IFeriadoRepositorio;

@Service
public class FeriadoServicio implements IFeriadoServicio{

	@Autowired
	private IFeriadoRepositorio feriadoRepo;
	
	@Override
	public List<Feriado> listarFeriados() {
		return feriadoRepo.findAll();
	}
	
}
