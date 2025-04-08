package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaDolares;
import com.mutual.SistemaAyudaCuotas.entidades.CuotaAyudaPesos;
import com.mutual.SistemaAyudaCuotas.entidades.MovimientoCajaAhorroComun;

@Repository
public interface ICuotaAyudaDolaresRepositorio extends JpaRepository<CuotaAyudaDolares, Long> {
	   @Query("SELECT m FROM MovimientoCajaAhorroComun m WHERE m.numeroSocio = :numeroSocio ORDER BY m.fecha DESC")
	    Optional<MovimientoCajaAhorroComun> findTopByNumeroSocioOrderByFechaDesc(@Param("numeroSocio") Integer numeroSocio);
	  
	   
	   List<CuotaAyudaDolares> findByNumeroAyuda(Integer numeroAyuda);
}
