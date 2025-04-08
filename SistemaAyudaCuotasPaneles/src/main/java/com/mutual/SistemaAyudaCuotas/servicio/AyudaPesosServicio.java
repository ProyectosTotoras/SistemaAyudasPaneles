package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutual.SistemaAyudaCuotas.entidades.AyudaPesos;
import com.mutual.SistemaAyudaCuotas.repositorio.IAyudaPesosRepositorio;

@Service
public class AyudaPesosServicio implements IAyudaPesosServicio{
	@Autowired
	private IAyudaPesosRepositorio ayudaRepositorio;

	@Override
	@Transactional
	public void crearAyuda(AyudaPesos ayuda) {
		ayudaRepositorio.save(ayuda);
		
	}

	@Override
	public AyudaPesos buscarAyudaPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AyudaPesos buscarAyudaPorNumeroAyuda(int id) {
		return ayudaRepositorio.findOneByNroAyuda(id).orElse(null);
	}

	@Override
	public List<AyudaPesos> listarAyudas() {
		return ayudaRepositorio.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<AyudaPesos> findTopByOrderByNumeroAyudaDesc() {
	
		return ayudaRepositorio.findTopByOrderByNroAyudaDesc();
	}

	@Override
	public List<AyudaPesos> buscarAyudasPorNumeroAyuda(Integer nroAyuda) {
		return ayudaRepositorio.findAllByNroAyuda(nroAyuda);
	}

	@Override
	public List<AyudaPesos> buscarAyudasPorNumeroSocio(Integer numeroSocio) {
		return ayudaRepositorio.findByNumeroSocio(numeroSocio);
	}

	@Override
	public List<AyudaPesos> buscarPorNumSocioNumAyuda(Integer numeroSocio, Integer nroAyuda) {
		return ayudaRepositorio.findByNumeroSocioAndNroAyuda(nroAyuda, numeroSocio);
	}

	@Override
	public void eliminarAyuda(Long idAyudaPesos) {
		ayudaRepositorio.deleteById(idAyudaPesos);
		
	}

    @Override
    @Transactional
    public AyudaPesos actualizarAyuda(AyudaPesos ayuda) {
        // If the entity already exists (ID is set), this will update it.
        return ayudaRepositorio.save(ayuda);
    }

    //FECHAS LEGALES EXISTENTES
	@Override
	public List<AyudaPesos> listarAyudasPorNumeroSocioFechaLegales(Integer numeroSocio) {
		return ayudaRepositorio.findByNumeroSocioWithLegalesDates(numeroSocio);
	}

	@Override
	public List<AyudaPesos> listarAyudasFechaLegales() {
	
		return ayudaRepositorio.findAllWithLegalesDates();
	}

	@Override
	public Optional<AyudaPesos> buscarAyudaPorNumeroAyudaFechaLegales(Integer numeroAyuda) {
		
		return ayudaRepositorio.findByNroAyudaWithLegalesDates(numeroAyuda);
	}
	
	//FECHAS LEGALES INEXISTENTES
	// Listar ayudas por número de socio sin fechas legales
	@Override
	public List<AyudaPesos> listarAyudasPorNumeroSocioSinFechaLegales(Integer numeroSocio) {
	    return ayudaRepositorio.findByNumeroSocioWithoutLegalesDates(numeroSocio);
	}

	// Listar todas las ayudas sin fechas legales
	@Override
	public List<AyudaPesos> listarAyudasSinFechaLegales() {
	    return ayudaRepositorio.findAllWithoutLegalesDates();
	}

	// Buscar ayuda por número de ayuda sin fechas legales
	@Override
	public Optional<AyudaPesos> buscarAyudaPorNumeroAyudaSinFechaLegales(Integer numeroAyuda) {
	    return ayudaRepositorio.findByNroAyudaWithoutLegalesDates(numeroAyuda);
	}

}
