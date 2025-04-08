package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;
import java.util.Optional;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;

public interface IAyudaDolaresServicio {
	//lectura
	public AyudaDolares buscarAyudaPorId(int id);
	public AyudaDolares buscarAyudaPorNumeroAyuda(int id);
	
	public List<AyudaDolares> listarAyudas();
	//Encontrar ayudas por numero de ayuda
	public List<AyudaDolares> buscarAyudasPorNumeroAyuda(Integer nroAyuda);
	//Encontrar ayudas por numero de socio
	public List<AyudaDolares> buscarAyudasPorNumeroSocio(Integer numeroSocio);
	//Encontrar ayudas por numero de socio y numero de ayuda
	public List<AyudaDolares> buscarPorNumSocioNumAyuda(Integer numeroSocio, Integer nroAyuda);
	
	public Optional<AyudaDolares> findTopByOrderByNumeroAyudaDesc();
	
	public void crearAyuda(AyudaDolares ayuda);
	
	public void eliminarAyuda(Long idAyudaDolares);
	

	 
	
}
