package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "AYUDAS_DOLARES")
public class AyudaDolares {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
	@Column(name = "ID_AYUDA_DOLARES")
    private Long idAyudaDolares;
	
	@Column(name = "NRO_AYUDA")
	private Integer nroAyuda; 						// NRO_AYUDA
	
	@Column(name = "NUMERO_SOCIO")
	private Integer numeroSocio; 					// SOCIO
	
	@Column(name = "FECHA_ALTA_AYUDA")
	@Temporal(TemporalType.DATE)
	private Date fechaAltaAyuda;					// FECHA
	
	@Column(name = "MONTO_SOLICITADO")
	private BigDecimal montoSolicitado;				// MONT_SOL
	
	@Column(name = "INTERES")
	private BigDecimal interes;						// INTER
	
	@Column(name = "CANTIDAD_CUOTAS")
	private Integer cantidadCuotas; 				// CAN_CUO
	
	@Column(name = "DESTINO_AYUDA")
	private String destinoAyuda; 					// DESTINO
	
	@Column(name = "NRO_SOCIO_GARANTE")
	private Integer nroSocioGarante; 				// GARANTE
	
	@Column(name = "MONTO_GASTO_ADMIN")
	private BigDecimal montoGastoAdministrativo;	// M_GASTO
	
	@Column(name = "MONTO_SELLADO")
	private BigDecimal montoSellado;				// M_COB
	
	@Column(name = "INTERES_CUOTA")
	private BigDecimal interesCuota;				// INT_CUO
	
	@Column(name = "INTERES_ACU")
	private BigDecimal interesAcu;					// INT_ACU
	
	@Column(name = "AYUDA_SALDADA")
	private String ayudaSaldada; 					// SALDO
	
	@Column(name = "FECHA_LEGALES")
	@Temporal(TemporalType.DATE)
	private Date fechaLegales;						// LEGALES
		
	@Column(name = "MONTO_SELLADO_PCIAL")
	private BigDecimal montoSelladoPcial;			// PCIAL
	
	@Column(name = "GARANTIA")
	private Integer garantia; 						// GARANTIA
	
	@Column(name = "OBSERVACIONES")
	private String observaciones; 					// OBSERVA
	
	@Column(name = "NRO_SOCIO_GARANTE_2")
	private Integer nroSocioGarante2; 				// GARANTE2
	
	@Column(name = "NRO_SOCIO_GARANTE_3")
	private Integer nroSocioGarante3; 				// No esta en la tabla, necesario a futuro
	
	@Column(name = "MONTO_EFECTIVO")
	private BigDecimal montoEfectivo;				// EFECTIVO
	
	@Column(name = "MONTO_TRANSFERENCIA")
	private BigDecimal montoTransferencia;			// TRANSFE
	
	@Column(name = "NRO_CHEQUE_1")
	private Integer nroCheque1; 					// NROCHE1
	
	@Column(name = "BANCO_CHEQUE_1")
	private String bancoCheque1; 					// BANCO1
	
	@Column(name = "MONTO_CHEQUE_1")
	private BigDecimal montoCheque1;				// CHEQUE1
	
	@Column(name = "NRO_CHEQUE_2")
	private Integer nroCheque2; 					// NROCHE2
	
	@Column(name = "BANCO_CHEQUE_2")
	private String bancoCheque2; 					// BANCO2
	
	@Column(name = "MONTO_CHEQUE_2")
	private BigDecimal montoCheque2;				// CHEQUE2
	
	@Column(name = "NRO_CHEQUE_3")
	private Integer nroCheque3; 					// NROCHE3
	
	@Column(name = "BANCO_CHEQUE_3")
	private String bancoCheque3; 					// BANCO3
	
	@Column(name = "MONTO_CHEQUE_3")
	private BigDecimal montoCheque3;				// CHEQUE3
	
	@Column(name = "NOVACION")
	private String novacion; 						// NOVACION
	
	@Column(name = "SEGURO")
	private String seguro; 							// SEGURO

	public AyudaDolares() {
		
	}

	public AyudaDolares(Long idAyudaDolares, Integer nroAyuda, Integer numeroSocio, Date fechaAltaAyuda,
			BigDecimal montoSolicitado, BigDecimal interes, Integer cantidadCuotas, String destinoAyuda,
			Integer nroSocioGarante, BigDecimal montoGastoAdministrativo, BigDecimal montoSellado,
			BigDecimal interesCuota, BigDecimal interesAcu, String ayudaSaldada, Date fechaLegales,
			BigDecimal montoSelladoPcial, Integer garantia, String observaciones, Integer nroSocioGarante2,
			Integer nroSocioGarante3, BigDecimal montoEfectivo, BigDecimal montoTransferencia, Integer nroCheque1,
			String bancoCheque1, BigDecimal montoCheque1, Integer nroCheque2, String bancoCheque2,
			BigDecimal montoCheque2, Integer nroCheque3, String bancoCheque3, BigDecimal montoCheque3, String novacion,
			String seguro) {
		super();
		this.idAyudaDolares = idAyudaDolares;
		this.nroAyuda = nroAyuda;
		this.numeroSocio = numeroSocio;
		this.fechaAltaAyuda = fechaAltaAyuda;
		this.montoSolicitado = montoSolicitado;
		this.interes = interes;
		this.cantidadCuotas = cantidadCuotas;
		this.destinoAyuda = destinoAyuda;
		this.nroSocioGarante = nroSocioGarante;
		this.montoGastoAdministrativo = montoGastoAdministrativo;
		this.montoSellado = montoSellado;
		this.interesCuota = interesCuota;
		this.interesAcu = interesAcu;
		this.ayudaSaldada = ayudaSaldada;
		this.fechaLegales = fechaLegales;
		this.montoSelladoPcial = montoSelladoPcial;
		this.garantia = garantia;
		this.observaciones = observaciones;
		this.nroSocioGarante2 = nroSocioGarante2;
		this.nroSocioGarante3 = nroSocioGarante3;
		this.montoEfectivo = montoEfectivo;
		this.montoTransferencia = montoTransferencia;
		this.nroCheque1 = nroCheque1;
		this.bancoCheque1 = bancoCheque1;
		this.montoCheque1 = montoCheque1;
		this.nroCheque2 = nroCheque2;
		this.bancoCheque2 = bancoCheque2;
		this.montoCheque2 = montoCheque2;
		this.nroCheque3 = nroCheque3;
		this.bancoCheque3 = bancoCheque3;
		this.montoCheque3 = montoCheque3;
		this.novacion = novacion;
		this.seguro = seguro;
	}

	public Long getIdAyudaDolares() {
		return idAyudaDolares;
	}

	public void setIdAyudaDolares(Long idAyudaDolares) {
		this.idAyudaDolares = idAyudaDolares;
	}

	public Integer getNroAyuda() {
		return nroAyuda;
	}

	public void setNroAyuda(Integer nroAyuda) {
		this.nroAyuda = nroAyuda;
	}

	public Integer getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(Integer numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public Date getFechaAltaAyuda() {
		return fechaAltaAyuda;
	}

	public void setFechaAltaAyuda(Date fechaAltaAyuda) {
		this.fechaAltaAyuda = fechaAltaAyuda;
	}

	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public Integer getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public String getDestinoAyuda() {
		return destinoAyuda;
	}

	public void setDestinoAyuda(String destinoAyuda) {
		this.destinoAyuda = destinoAyuda;
	}

	public Integer getNroSocioGarante() {
		return nroSocioGarante;
	}

	public void setNroSocioGarante(Integer nroSocioGarante) {
		this.nroSocioGarante = nroSocioGarante;
	}

	public BigDecimal getMontoGastoAdministrativo() {
		return montoGastoAdministrativo;
	}

	public void setMontoGastoAdministrativo(BigDecimal montoGastoAdministrativo) {
		this.montoGastoAdministrativo = montoGastoAdministrativo;
	}

	public BigDecimal getMontoSellado() {
		return montoSellado;
	}

	public void setMontoSellado(BigDecimal montoSellado) {
		this.montoSellado = montoSellado;
	}

	public BigDecimal getInteresCuota() {
		return interesCuota;
	}

	public void setInteresCuota(BigDecimal interesCuota) {
		this.interesCuota = interesCuota;
	}

	public BigDecimal getInteresAcu() {
		return interesAcu;
	}

	public void setInteresAcu(BigDecimal interesAcu) {
		this.interesAcu = interesAcu;
	}

	public String getAyudaSaldada() {
		return ayudaSaldada;
	}

	public void setAyudaSaldada(String ayudaSaldada) {
		this.ayudaSaldada = ayudaSaldada;
	}

	public Date getFechaLegales() {
		return fechaLegales;
	}

	public void setFechaLegales(Date fechaLegales) {
		this.fechaLegales = fechaLegales;
	}

	public BigDecimal getMontoSelladoPcial() {
		return montoSelladoPcial;
	}

	public void setMontoSelladoPcial(BigDecimal montoSelladoPcial) {
		this.montoSelladoPcial = montoSelladoPcial;
	}

	public Integer getGarantia() {
		return garantia;
	}

	public void setGarantia(Integer garantia) {
		this.garantia = garantia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getNroSocioGarante2() {
		return nroSocioGarante2;
	}

	public void setNroSocioGarante2(Integer nroSocioGarante2) {
		this.nroSocioGarante2 = nroSocioGarante2;
	}

	public Integer getNroSocioGarante3() {
		return nroSocioGarante3;
	}

	public void setNroSocioGarante3(Integer nroSocioGarante3) {
		this.nroSocioGarante3 = nroSocioGarante3;
	}

	public BigDecimal getMontoEfectivo() {
		return montoEfectivo;
	}

	public void setMontoEfectivo(BigDecimal montoEfectivo) {
		this.montoEfectivo = montoEfectivo;
	}

	public BigDecimal getMontoTransferencia() {
		return montoTransferencia;
	}

	public void setMontoTransferencia(BigDecimal montoTransferencia) {
		this.montoTransferencia = montoTransferencia;
	}

	public Integer getNroCheque1() {
		return nroCheque1;
	}

	public void setNroCheque1(Integer nroCheque1) {
		this.nroCheque1 = nroCheque1;
	}

	public String getBancoCheque1() {
		return bancoCheque1;
	}

	public void setBancoCheque1(String bancoCheque1) {
		this.bancoCheque1 = bancoCheque1;
	}

	public BigDecimal getMontoCheque1() {
		return montoCheque1;
	}

	public void setMontoCheque1(BigDecimal montoCheque1) {
		this.montoCheque1 = montoCheque1;
	}

	public Integer getNroCheque2() {
		return nroCheque2;
	}

	public void setNroCheque2(Integer nroCheque2) {
		this.nroCheque2 = nroCheque2;
	}

	public String getBancoCheque2() {
		return bancoCheque2;
	}

	public void setBancoCheque2(String bancoCheque2) {
		this.bancoCheque2 = bancoCheque2;
	}

	public BigDecimal getMontoCheque2() {
		return montoCheque2;
	}

	public void setMontoCheque2(BigDecimal montoCheque2) {
		this.montoCheque2 = montoCheque2;
	}

	public Integer getNroCheque3() {
		return nroCheque3;
	}

	public void setNroCheque3(Integer nroCheque3) {
		this.nroCheque3 = nroCheque3;
	}

	public String getBancoCheque3() {
		return bancoCheque3;
	}

	public void setBancoCheque3(String bancoCheque3) {
		this.bancoCheque3 = bancoCheque3;
	}

	public BigDecimal getMontoCheque3() {
		return montoCheque3;
	}

	public void setMontoCheque3(BigDecimal montoCheque3) {
		this.montoCheque3 = montoCheque3;
	}

	public String getNovacion() {
		return novacion;
	}

	public void setNovacion(String novacion) {
		this.novacion = novacion;
	}

	public String getSeguro() {
		return seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

}