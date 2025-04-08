package com.mutual.SistemaAyudaCuotas.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.SistemaAyudaCuotas.entidades.Cuota;
import com.mutual.SistemaAyudaCuotas.repositorio.ICuotaRepositorio;

@Service
public class CuotaServicio implements ICuotaServicio{
	@Autowired
	private ICuotaRepositorio cuotaRepositorio;

	@Override
	public List<Cuota> buscarCuotasPorNumeroSocio(Integer numeroSocio) {
		return cuotaRepositorio.findByNumeroSocio(numeroSocio);
	}

	@Override
	public void borrarCuota(Cuota cuota) {
		cuotaRepositorio.delete(cuota);
	}

	@Override
	public List<Cuota> buscarCuotasPorNumeroSocioHastaMesActual(Integer numeroSocio) {
	    List<Cuota> listaCuotas = cuotaRepositorio.findByNumeroSocio(numeroSocio);
	    List<Cuota> listaCuotasAdeudadas = new ArrayList<>();
	    
	    LocalDate fechaActual = LocalDate.now();
	    int mesActual = fechaActual.getMonthValue();
	    int anioActual = fechaActual.getYear();
	    
	    
	    
	    listaCuotas.forEach(c -> {
	    	int mesCuota = c.getMes();
	    	int anioCuota = c.getAno();
	    	
	    	if(anioCuota < anioActual) {
	    		
	    		listaCuotasAdeudadas.add(c);
	    		
	    	}else if(anioCuota == anioActual && mesCuota <= mesActual) {
	    		listaCuotasAdeudadas.add(c);
	    	}
	    });
	    
	    listaCuotas.removeIf(c -> (c.getAno() < anioActual) || (c.getAno() == anioActual && c.getMes() <= mesActual) );
	    return listaCuotasAdeudadas;
	}


}
