package com.mutual.SistemaAyudaCuotas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;


@Repository
public interface IAyudaPesosRepositorio extends JpaRepository<AyudaPesos, Long> {

	//Encontrar la ultima ayuda
	 Optional<AyudaPesos> findTopByOrderByNroAyudaDesc();
 
	 Optional<AyudaPesos> findOneByNroAyuda(Integer nroAyuda);
	 
	 //Encontrar ayudas por numero de socio
	 List<AyudaPesos> findByNumeroSocio(Integer numeroSocio);

	 //Encontrar ayudas por numero de ayuda
	 List<AyudaPesos> findAllByNroAyuda(Integer nroAyuda);
	 
	 //Encontrar ayudas por numero de socio y numero de ayuda
	 List<AyudaPesos> findByNumeroSocioAndNroAyuda(Integer numeroSocio, Integer nroAyuda);
	 
	 @Query("SELECT a FROM AyudaPesos a WHERE a.numeroSocio = :numeroSocio AND (a.fechaLegales IS NOT NULL OR a.fechaOrigLegales IS NOT NULL)")
	 List<AyudaPesos> findByNumeroSocioWithLegalesDates(@Param("numeroSocio") Integer numeroSocio);

	 @Query("SELECT a FROM AyudaPesos a WHERE a.fechaLegales IS NOT NULL OR a.fechaOrigLegales IS NOT NULL")
	 List<AyudaPesos> findAllWithLegalesDates();

	 List<AyudaPesos> findByNumeroSocioAndFechaLegalesIsNotNullOrFechaOrigLegalesIsNotNull(Integer numeroSocio);

	 @Query("SELECT a FROM AyudaPesos a WHERE a.nroAyuda = :nroAyuda AND (a.fechaLegales IS NOT NULL OR a.fechaOrigLegales IS NOT NULL)")
	 Optional<AyudaPesos> findByNroAyudaWithLegalesDates(@Param("nroAyuda") Integer nroAyuda);

	 
	 	// Por número de socio: devuelve las ayudas que no tengan ni fechaLegales ni fechaOrigLegales
		@Query("SELECT a FROM AyudaPesos a WHERE a.numeroSocio = :numeroSocio AND (a.fechaLegales IS NULL AND a.fechaOrigLegales IS NULL)")
		List<AyudaPesos> findByNumeroSocioWithoutLegalesDates(@Param("numeroSocio") Integer numeroSocio);

		// Todas las ayudas sin fechas legales: ni fechaLegales ni fechaOrigLegales establecidas
		@Query("SELECT a FROM AyudaPesos a WHERE (a.fechaLegales IS NULL AND a.fechaOrigLegales IS NULL)")
		List<AyudaPesos> findAllWithoutLegalesDates();

		// Por número de ayuda: devuelve la ayuda que no tenga ninguna de las fechas legales
		@Query("SELECT a FROM AyudaPesos a WHERE a.nroAyuda = :nroAyuda AND (a.fechaLegales IS NULL AND a.fechaOrigLegales IS NULL)")
		Optional<AyudaPesos> findByNroAyudaWithoutLegalesDates(@Param("nroAyuda") Integer nroAyuda);

}
