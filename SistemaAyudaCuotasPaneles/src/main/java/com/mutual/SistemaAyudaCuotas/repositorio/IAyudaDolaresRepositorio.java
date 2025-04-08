package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;

@Repository
public interface IAyudaDolaresRepositorio extends JpaRepository<AyudaDolares, Long>{
	//Encontrar la ultima ayuda
	 Optional<AyudaDolares> findTopByOrderByNroAyudaDesc();

	 Optional<AyudaDolares> findOneByNroAyuda(Integer nroAyuda);
	 
	 //Encontrar ayudas por numero de socio
	 List<AyudaDolares> findByNumeroSocio(Integer numeroSocio);

	 //Encontrar ayudas por numero de ayuda
	 List<AyudaDolares> findAllByNroAyuda(Integer nroAyuda);
	 
	 //Encontrar ayudas por numero de socio y numero de ayuda
	 List<AyudaDolares> findByNumeroSocioAndNroAyuda(Integer numeroSocio, Integer nroAyuda);
}
