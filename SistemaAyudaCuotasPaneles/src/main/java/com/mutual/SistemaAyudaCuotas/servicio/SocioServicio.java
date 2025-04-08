package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutual.SistemaAyudaCuotas.entidades.Socio;
import com.mutual.SistemaAyudaCuotas.repositorio.ISocioRepositorio;

@Service
public class SocioServicio implements ISocioServicio{
	@Autowired
	private ISocioRepositorio socioRepositorio;

	@Override
	public void crearSocio(Socio socio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Socio> listarSocios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Socio buscarSocioPorNumeroSocio(int numeroSocio) {
		return socioRepositorio.findById(numeroSocio).orElse(null);
	}
	

    // New method to fetch multiple Socio records in one query
    @Transactional(readOnly = true)
    public List<Socio> buscarSociosPorNumeros(List<Integer> numerosSocio) {
        return socioRepositorio.findByNumeroSocioIn(numerosSocio);
    }
	
}
