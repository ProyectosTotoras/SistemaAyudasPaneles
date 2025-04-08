package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.Parametro;

public interface IParametroServicio {
	List<Parametro> listarParametros();
	Parametro traerParametro(Long id);
	
}
