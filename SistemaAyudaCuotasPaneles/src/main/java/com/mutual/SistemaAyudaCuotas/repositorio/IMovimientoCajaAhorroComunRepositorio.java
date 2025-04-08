package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.MovimientoCajaAhorroComun;

@Repository
public interface IMovimientoCajaAhorroComunRepositorio extends JpaRepository<MovimientoCajaAhorroComun, Long>{

	 List<MovimientoCajaAhorroComun> findByNumeroSocio(Integer numeroSocio);

}
