package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;

@Repository
public interface ICuotasRepositorio extends JpaRepository<CuotaAyudaPesos, Integer>{
	//Encontrar ayudas por numero de ayuda
		 List<CuotaAyudaPesos> findByNumeroAyuda(Integer numeroAyuda);
		
	
}
