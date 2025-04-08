package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.Cuota;

@Repository
public interface ICuotaRepositorio extends JpaRepository<Cuota, Long> {
	
	   List<Cuota> findByNumeroSocio(Integer numeroSocio);
	   
	   List<Cuota> findByNumeroSocioOrderByMesAsc(Integer numeroSocio);
	   

}
