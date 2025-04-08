package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mutual.SistemaAyudaCuotas.entidades.Socio;

public interface ISocioServicio {
	public void crearSocio(Socio socio);
	public List<Socio> listarSocios();
	public Socio buscarSocioPorNumeroSocio(int numeroSocio);
	public List<Socio> buscarSociosPorNumeros(List<Integer> numerosSocio);
    
}
