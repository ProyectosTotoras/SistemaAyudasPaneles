package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;

public interface IAyudaPesosServicio {
	//lectura
	public AyudaPesos buscarAyudaPorId(int id);
	public AyudaPesos buscarAyudaPorNumeroAyuda(int id);
	
	public List<AyudaPesos> listarAyudas();
	//Encontrar ayudas por numero de ayuda
	public List<AyudaPesos> buscarAyudasPorNumeroAyuda(Integer nroAyuda);
	//Encontrar ayudas por numero de socio
	public List<AyudaPesos> buscarAyudasPorNumeroSocio(Integer numeroSocio);
	//Encontrar ayudas por numero de socio y numero de ayuda
	public List<AyudaPesos> buscarPorNumSocioNumAyuda(Integer numeroSocio, Integer nroAyuda);
	
	public Optional<AyudaPesos> findTopByOrderByNumeroAyudaDesc();
	
	public void crearAyuda(AyudaPesos ayuda);
	
	public void eliminarAyuda(Long idAyudaPesos);
	
	public AyudaPesos actualizarAyuda(AyudaPesos ayuda);
	 
	List<AyudaPesos> listarAyudasPorNumeroSocioFechaLegales(Integer numeroSocio);
	
	List<AyudaPesos> listarAyudasFechaLegales();
	
	Optional<AyudaPesos> buscarAyudaPorNumeroAyudaFechaLegales(Integer numeroAyuda);

	public List<AyudaPesos> listarAyudasPorNumeroSocioSinFechaLegales(Integer numeroSocio);

	public List<AyudaPesos> listarAyudasSinFechaLegales();
	
	public Optional<AyudaPesos> buscarAyudaPorNumeroAyudaSinFechaLegales(Integer numeroAyuda);

}
