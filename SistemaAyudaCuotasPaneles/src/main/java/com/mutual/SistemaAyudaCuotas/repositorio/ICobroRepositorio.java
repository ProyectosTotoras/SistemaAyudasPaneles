package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.Cobro;

@Repository
public interface ICobroRepositorio extends JpaRepository<Cobro, Long>{
	List<Cobro> findByNumeroSocio(Integer numeroSocio);
	Cobro findOneByNumeroSocio(Integer numeroSocio);
}
