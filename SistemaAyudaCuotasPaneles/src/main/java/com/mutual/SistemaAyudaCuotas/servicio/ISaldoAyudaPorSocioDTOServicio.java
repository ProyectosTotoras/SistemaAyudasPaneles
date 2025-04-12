package com.mutual.SistemaAyudaCuotas.servicio;

import java.util.List;

import com.mutual.SistemaAyudaCuotas.entidades.SaldoAyudaPorSocioDTO;

public interface ISaldoAyudaPorSocioDTOServicio {
	List<SaldoAyudaPorSocioDTO> findSaldoAyuda();
}
