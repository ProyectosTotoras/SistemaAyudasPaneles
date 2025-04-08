package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutual.SistemaAyudaCuotas.entidades.MovimientoCajaAhorroComun;
import com.mutual.SistemaAyudaCuotas.repositorio.IMovimientoCajaAhorroComunRepositorio;

@Service
public class MovimientoCajaAhorroComunServicio implements IMovimientoCajaAhorroComunServicio{

	
	@Autowired
	private IMovimientoCajaAhorroComunRepositorio movimientoCajaAhorroComunRepositorio;
	
	@Override
	@Transactional(readOnly = true)
	public List<MovimientoCajaAhorroComun> findByNumeroSocio(Integer numeroSocio) {
		return  movimientoCajaAhorroComunRepositorio.findByNumeroSocio(numeroSocio);
	}

}
