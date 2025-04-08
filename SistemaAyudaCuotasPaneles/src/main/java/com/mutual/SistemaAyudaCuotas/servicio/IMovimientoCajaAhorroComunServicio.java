package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.MovimientoCajaAhorroComun;

public interface IMovimientoCajaAhorroComunServicio {
	List<MovimientoCajaAhorroComun> findByNumeroSocio(Integer numeroSocio);
}
