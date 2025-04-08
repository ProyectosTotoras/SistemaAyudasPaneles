package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.CobroCuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;

@Repository
public interface ICobroCuotaAyudaPesosRepositorio extends JpaRepository<CobroCuotaAyudaPesos, Long>{
	boolean existsByNumeroAyudaAndNumeroCuota(Integer nroAyuda, Integer numeroCuota);
	List<CobroCuotaAyudaPesos> findByNumeroAyuda(Integer numeroAyuda);
	CobroCuotaAyudaPesos findFirstByNumeroAyuda(Integer numeroAyuda);
}
