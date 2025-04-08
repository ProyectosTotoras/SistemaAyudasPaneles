package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.Socio;

@Repository
public interface ISocioRepositorio extends JpaRepository<Socio, Integer> {
	List<Socio> findByNumeroSocioIn(Collection<Integer> numeros);
}
