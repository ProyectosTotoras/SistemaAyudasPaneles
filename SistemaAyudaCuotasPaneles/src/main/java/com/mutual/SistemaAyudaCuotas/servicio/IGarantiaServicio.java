package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.Garantia;

public interface IGarantiaServicio {
	List<Garantia> listarGarantias();
	Garantia buscarGarantiaPorId(Integer id);
}
