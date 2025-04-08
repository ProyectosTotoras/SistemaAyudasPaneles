package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.Cobro;

public interface ICobroServicio {
	List<Cobro> listarCobros();
	List<Cobro> listarCobroPorNumeroSocio(int numeroSocio);
	Cobro buscarCobroPorNumeroSocio(int numeroSocio);
	Cobro crearCobro(Cobro cobro);
}
