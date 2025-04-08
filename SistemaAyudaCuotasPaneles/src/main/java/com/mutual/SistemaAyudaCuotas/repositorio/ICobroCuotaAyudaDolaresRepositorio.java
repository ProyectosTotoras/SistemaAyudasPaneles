package com.mutual.SistemaAyudaCuotas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaDolares;

@Repository
public interface ICobroCuotaAyudaDolaresRepositorio extends JpaRepository<CobroCuotaAyudaDolares, Long>{
	boolean existsByNumeroAyudaAndNumeroCuota(Integer nroAyuda, Integer numeroCuota);

}
