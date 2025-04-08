package com.mutual.SistemaAyudaCuotas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.Parametro;

@Repository
public interface IParemetroRepositorio extends JpaRepository<Parametro, Long>{

	
}
