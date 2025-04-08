package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutual.SistemaAyudaCuotas.entidades.Garantia;
import com.mutual.SistemaAyudaCuotas.repositorio.IGarantiaRepositorio;

@Service
public class GarantiaServicio implements IGarantiaServicio{

	@Autowired
	private IGarantiaRepositorio garantiaRepo;

	@Override
	@Transactional
	public List<Garantia> listarGarantias() {
		
		return garantiaRepo.findAll();
	}


	@Override
	public Garantia buscarGarantiaPorId(Integer id) {
		// TODO Auto-generated method stub
		return garantiaRepo.findById(id).orElse(null);
	}

}
