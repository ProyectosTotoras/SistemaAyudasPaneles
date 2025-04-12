package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.SaldoAyudaPorSocioDTO;
import com.mutual.SistemaAyudaCuotas.repositorio.IAyudaPesosRepositorio;

@Service
public class SaldoAyudaPorSocioDTOServicio implements ISaldoAyudaPorSocioDTOServicio{

	@Autowired
	private IAyudaPesosRepositorio repositorio;
	
	@Override
	public List<SaldoAyudaPorSocioDTO> findSaldoAyuda() {
		// TODO Auto-generated method stub
		return repositorio.findSaldoAyuda();
	}

}
