package com.mutual.SistemaAyudaCuotas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.Garantia;

@Repository		
public interface IGarantiaRepositorio extends JpaRepository<Garantia, Integer>{

}
