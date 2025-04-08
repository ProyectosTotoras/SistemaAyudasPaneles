package com.mutual.SistemaAyudaCuotas.entidades;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SOCIO")
public class Socio {
	
	@Id
	@Column(name = "NUMERO_SOCIO")
	private Integer numeroSocio; // SOCIO

	@Column(name = "APELLIDO_NOMBRE", columnDefinition = "TEXT")
	private String apellidoNombre; // APENOM

	@Column(name = "DOMICILIO", columnDefinition = "TEXT")
	private String domicilio; // DOMICILIO

	@Column(name = "LOCALIDAD", columnDefinition = "TEXT")
	private String localidad; // LOCALIDAD

	@Column(name = "CODIGO_POSTAL")
	private Integer codigoPostal; // CP

	@Column(name = "TIPO_DOCUMENTO", columnDefinition = "TEXT")
	private String tipoDocumento; // TIPDOC

	@Column(name = "NRO_DOCUMENTO")
	private Integer numeroDocumento; // NRODOC

	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento; // FECH_NAC

	@Column(name = "ESTADO_CIVIL", columnDefinition = "TEXT")
	private String estadoCivil; // ESTADO_CIV

	@Column(name = "PROFESION", columnDefinition = "TEXT")
	private String profesion; // PROFESION

	@Column(name = "CATEGORIA", columnDefinition = "TEXT")
	private String categoria; // CATEGORIA

	@Column(name = "FECHA_INGRESO")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso; // FECH_INGRE

	@Column(name = "MONTO_MAXIMO")
	private BigDecimal montoMaximo; // MONT_MAX

	@Column(name = "FECHA_EGRESO")
	@Temporal(TemporalType.DATE)
	private Date fechaEgreso; // FECH_EGRE

	@Column(name = "FECHA_NACIMIENTO_2")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento_2; // FECHNAC

	@Column(name = "FECHA_INGRESO_2")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso_2; // FECHINGRE

	@Column(name = "FECHA_EGRESO_2")
	@Temporal(TemporalType.DATE)
	private Date fechaEgreso_2; // FECHAEGRE

	@Column(name = "CUIT", columnDefinition = "TEXT")
	private String cuit; // CUIT

	@Column(name = "RESPONSABLE", columnDefinition = "TEXT")
	private String responsable; // RESPO

	@Column(name = "TELEFONO", columnDefinition = "TEXT")
	private String telefono; // TEL

	@Column(name = "EMAIL", columnDefinition = "TEXT")
	private String email; // EMAIL

	@Column(name = "NACIONALIDAD", columnDefinition = "TEXT")
	private String nacionalidad; // NACIONALID

	@Column(name = "CENSO")
	@Temporal(TemporalType.DATE)
	private Date censo; // CENSO

	@Column(name = "TRABAJO", columnDefinition = "TEXT")
	private String trabajo; // TRABAJO

	@Column(name = "APELLIDO_PADRE", columnDefinition = "TEXT")
	private String apellidoPadre; // APELLPADRE

	@Column(name = "NOMBRE_PADRE", columnDefinition = "TEXT")
	private String nombrePadre; // NOMBRPADRE

	@Column(name = "VIVE_PADRE", columnDefinition = "TEXT")
	private String vivePadre; // VIVEP

	@Column(name = "APELLIDO_MADRE", columnDefinition = "TEXT")
	private String apellidoMadre; // APELLMADRE

	@Column(name = "NOMBRE_MADRE", columnDefinition = "TEXT")
	private String nombreMadre; // NOMBRMADRE

	@Column(name = "VIVE_MADRE", columnDefinition = "TEXT")
	private String viveMadre; // VIVEM

	@Column(name = "APELLIDO_BENEFICIARIO", columnDefinition = "TEXT")
	private String apellidoBeneficiario; // APELLBENEF

	@Column(name = "NOMBRE_BENEFICIARIO", columnDefinition = "TEXT")
	private String nombreBeneficiario; // NOMBRBENEF

	@Column(name = "CARENCIA", columnDefinition = "TEXT")
	private String carencia; // CARENCIA

	@Column(name = "APELLIDO_CASADO", columnDefinition = "TEXT")
	private String apellidoCasado; // APELLCASAD

	@Column(name = "TIPPER", columnDefinition = "TEXT")
	private String tipper; // TIPPER

	@Column(name = "CONJUNTA", columnDefinition = "TEXT")
	private String conjunta; // CONJUNTA

	@Column(name = "LEGAJO", columnDefinition = "TEXT")
	private String legajo; // LEGAJO

	@Column(name = "COMPLETO", columnDefinition = "TEXT")
	private String completo; // COMPLETO

	@Column(name = "OPERADOR", columnDefinition = "TEXT")
	private String operador; // OPERADOR

	@Column(name = "FECHA_VENCIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento; // FECHAVTO

	@Column(name = "DOMICILIO_CONTRAC", columnDefinition = "TEXT")
	private String domicilioContractual; // DOMCONTRAC

	@Column(name = "LISTADO", columnDefinition = "TEXT")
	private String listado; // LISTADO

	@Column(name = "GARANTIAS", columnDefinition = "TEXT")
	private String garantias; // GARANTIAS

	@Column(name = "CONSTANCIA_INSC", columnDefinition = "TEXT")
	private String constanciaInscripto; // CONST_INSC

	@Column(name = "DDJJ_IIBB", columnDefinition = "TEXT")
	private String ddjj_iibb; // DDJJ_IIBB

	@Column(name = "DDJJ_GANANCIAS", columnDefinition = "TEXT")
	private String ddjj_ganancias; // DDJJ_GCIAS

	@Column(name = "DDJJ_BIENES_PERS", columnDefinition = "TEXT")
	private String ddjj_bienesPersonales; // DDJJ_BSPER

	@Column(name = "PAGO_MONOTRIBUTO", columnDefinition = "TEXT")
	private String pagoMonotributo; // PAGO_MONOT

	@Column(name = "PAGO_IVA", columnDefinition = "TEXT")
	private String pagoIVA; // PAGO_IVA

	@Column(name = "PAGO_IIBB", columnDefinition = "TEXT")
	private String pagoIIBB; // PAGO_IIBB

	@Column(name = "RECIBO_SUELDO", columnDefinition = "TEXT")
	private String reciboSueldo; // RECI_SUELD

	@Column(name = "FOTOCOPIA_DNI", columnDefinition = "TEXT")
	private String fotocopiaDNI; // FOTOC_DNI

	@Column(name = "FOTOCOPIA_IMPUESTO", columnDefinition = "TEXT")
	private String fotocopiaImpuesto; // FOTOC_IMPU

	@Column(name = "LICI_FONDO", columnDefinition = "TEXT")
	private String liciFondo; // LICI_FONDO

	@Column(name = "PEP", columnDefinition = "TEXT")
	private String pep; // PEP

	@Column(name = "PERFIL")
	private BigDecimal perfil; // PERFIL

	@Column(name = "RIESGO", columnDefinition = "TEXT")
	private String riesgo; // RIESGO

	@Column(name = "MODALIDAD", columnDefinition = "TEXT")
	private String modalidad; // MODALIDAD

	@Column(name = "TERROR", columnDefinition = "TEXT")
	private String terror; // TERROR

	@Column(name = "SEXO", columnDefinition = "TEXT")
	private String sexo; // SEXO

	@Column(name = "OBLIGADO", columnDefinition = "TEXT")
	private String obligado; // OBLIGADO

	@Column(name = "VTOOBL")
	@Temporal(TemporalType.DATE)
	private Date vencimientoObl; // VTOOBL

	@Column(name = "VTOPEP")
	@Temporal(TemporalType.DATE)
	private Date vencimientoPep; // VTOPEP

	@Column(name = "VTOTER")
	@Temporal(TemporalType.DATE)
	private Date vencimientoTer; // VTOTER

	@Column(name = "PROVINCIA", columnDefinition = "TEXT")
	private String provincia; // PROVINCIA

	@Column(name = "BENEFICIARIO_FINAL", columnDefinition = "TEXT")
	private String beneficiarioFinal; // BENEFFINAL

	@Column(name = "EMAIL_1", columnDefinition = "TEXT")
	private String email1; // EMAIL1

	@Column(name = "DIGITAL", columnDefinition = "TEXT")
	private String digital; // DIGITAL

	@Column(name = "MANDAR", columnDefinition = "TEXT")
	private String mandar; // MANDAR

	@Column(name = "ACRED", columnDefinition = "TEXT")
	private String acred; // ACRED

	@Column(name = "APODE", columnDefinition = "TEXT")
	private String apode; // APODE

	@Column(name = "PODER", columnDefinition = "TEXT")
	private String poder; // PODER

	@Column(name = "APODE_SOC_1")
	private Integer apodeSocio1; // APODESOC1

	@Column(name = "APODE_SOC_2")
	private Integer apodeSocio2; // APODESOC2

	@Column(name = "APODE_SOC_3")
	private Integer apodeSocio3; // APODESOC3

	@Column(name = "PAIS", columnDefinition = "TEXT")
	private String pais; // PAIS

	@Column(name = "LOCALIDAD_CONTR", columnDefinition = "TEXT")
	private String localidadCont; // LOCCONTR

	@Column(name = "PROVINCIA_CONTR", columnDefinition = "TEXT")
	private String provinciaCont; // PCIACONT

	@Column(name = "PAIS_CONTR", columnDefinition = "TEXT")
	private String paisCont; // PAISCONT

	@Column(name = "CELULAR", columnDefinition = "TEXT")
	private String celular; // CELULAR

	@Column(name = "LUGAR", columnDefinition = "TEXT")
	private String lugar; // LUGAR

	@Column(name = "VENCIMIENTO_RUPP")
	@Temporal(TemporalType.DATE)
	private Date vencimientoRUPP; // VTORUPP

	@Column(name = "DOM_GT1", columnDefinition = "TEXT")
	private String domGt1; // DOMGT1

	@Column(name = "TIPO_GT1", columnDefinition = "TEXT")
	private String tipoGt1; // TIPGT1

	@Column(name = "M1", columnDefinition = "TEXT")
	private String m1; // M1

	@Column(name = "MONTO_GT1")
	private BigDecimal montoGt1; // MONTGT1

	@Column(name = "VENCIMIENTO_GT1")
	@Temporal(TemporalType.DATE)
	private Date vencimientoGt1; // VTOGT1

	@Column(name = "DOM_GT2", columnDefinition = "TEXT")
	private String domGt2; // DOMGT2

	@Column(name = "TIPO_GT2", columnDefinition = "TEXT")
	private String tipoGt2; // TIPGT2

	@Column(name = "M2", columnDefinition = "TEXT")
	private String m2; // M2

	@Column(name = "MONTO_GT2")
	private BigDecimal montoGt2; // MONTGT2

	@Column(name = "VENCIMIENTO_GT2")
	@Temporal(TemporalType.DATE)
	private Date vencimientoGt2; // VTOGT2

	@Column(name = "DOM_GT3", columnDefinition = "TEXT")
	private String domGt3; // DOMGT3

	@Column(name = "TIPO_GT3", columnDefinition = "TEXT")
	private String tipoGt3; // TIPGT3

	@Column(name = "M3", columnDefinition = "TEXT")
	private String m3; // M3

	@Column(name = "MONTO_GT3")
	private BigDecimal montoGt3; // MONTGT3

	@Column(name = "VENCIMIENTO_GT3")
	@Temporal(TemporalType.DATE)
	private Date vencimientoGt3; // VTOGT3

	@Column(name = "ACTI_1", columnDefinition = "TEXT")
	private String acti1; // ACTI1

	@Column(name = "ACTI_2", columnDefinition = "TEXT")
	private String acti2; // ACTI2

	@Column(name = "PROPO", columnDefinition = "TEXT")
	private String propo; // PROPO

	@Column(name = "CANAL", columnDefinition = "TEXT")
	private String canal; // CANAL

	@Column(name = "FECHA_VENCIMIENTO_TOPE")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimientoTope; // FECVTOPE

	public Socio() {
	}

	// Constructors, getters and setters ...



	public Socio(Integer numeroSocio, String apellidoNombre, String domicilio, String localidad, Integer codigoPostal,
			String tipoDocumento, Integer numeroDocumento, Date fechaNacimiento, String estadoCivil, String profesion,
			String categoria, Date fechaIngreso, BigDecimal montoMaximo, Date fechaEgreso, Date fechaNacimiento_2,
			Date fechaIngreso_2, Date fechaEgreso_2, String cuit, String responsable, String telefono, String email,
			String nacionalidad, Date censo, String trabajo, String apellidoPadre, String nombrePadre, String vivePadre,
			String apellidoMadre, String nombreMadre, String viveMadre, String apellidoBeneficiario,
			String nombreBeneficiario, String carencia, String apellidoCasado, String tipper, String conjunta,
			String legajo, String completo, String operador, Date fechaVencimiento, String domicilioContractual,
			String listado, String garantias, String constanciaInscripto, String ddjj_iibb, String ddjj_ganancias,
			String ddjj_bienesPersonales, String pagoMonotributo, String pagoIVA, String pagoIIBB, String reciboSueldo,
			String fotocopiaDNI, String fotocopiaImpuesto, String liciFondo, String pep, BigDecimal perfil,
			String riesgo, String modalidad, String terror, String sexo, String obligado, Date vencimientoObl,
			Date vencimientoPep, Date vencimientoTer, String provincia, String beneficiarioFinal, String email1,
			String digital, String mandar, String acred, String apode, String poder, Integer apodeSocio1,
			Integer apodeSocio2, Integer apodeSocio3, String pais, String localidadCont, String provinciaCont,
			String paisCont, String celular, String lugar, Date vencimientoRUPP, String domGt1, String tipoGt1,
			String m1, BigDecimal montoGt1, Date vencimientoGt1, String domGt2, String tipoGt2, String m2,
			BigDecimal montoGt2, Date vencimientoGt2, String domGt3, String tipoGt3, String m3, BigDecimal montoGt3,
			Date vencimientoGt3, String acti1, String acti2, String propo, String canal, Date fechaVencimientoTope) {

		super();
		this.numeroSocio = numeroSocio;
		this.apellidoNombre = apellidoNombre;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.estadoCivil = estadoCivil;
		this.profesion = profesion;
		this.categoria = categoria;
		this.fechaIngreso = fechaIngreso;
		this.montoMaximo = montoMaximo;
		this.fechaEgreso = fechaEgreso;
		this.fechaNacimiento_2 = fechaNacimiento_2;
		this.fechaIngreso_2 = fechaIngreso_2;
		this.fechaEgreso_2 = fechaEgreso_2;
		this.cuit = cuit;
		this.responsable = responsable;
		this.telefono = telefono;
		this.email = email;
		this.nacionalidad = nacionalidad;
		this.censo = censo;
		this.trabajo = trabajo;
		this.apellidoPadre = apellidoPadre;
		this.nombrePadre = nombrePadre;
		this.vivePadre = vivePadre;
		this.apellidoMadre = apellidoMadre;
		this.nombreMadre = nombreMadre;
		this.viveMadre = viveMadre;
		this.apellidoBeneficiario = apellidoBeneficiario;
		this.nombreBeneficiario = nombreBeneficiario;
		this.carencia = carencia;
		this.apellidoCasado = apellidoCasado;
		this.tipper = tipper;
		this.conjunta = conjunta;
		this.legajo = legajo;
		this.completo = completo;
		this.operador = operador;
		this.fechaVencimiento = fechaVencimiento;
		this.domicilioContractual = domicilioContractual;
		this.listado = listado;
		this.garantias = garantias;
		this.constanciaInscripto = constanciaInscripto;
		this.ddjj_iibb = ddjj_iibb;
		this.ddjj_ganancias = ddjj_ganancias;
		this.ddjj_bienesPersonales = ddjj_bienesPersonales;
		this.pagoMonotributo = pagoMonotributo;
		this.pagoIVA = pagoIVA;
		this.pagoIIBB = pagoIIBB;
		this.reciboSueldo = reciboSueldo;
		this.fotocopiaDNI = fotocopiaDNI;
		this.fotocopiaImpuesto = fotocopiaImpuesto;
		this.liciFondo = liciFondo;
		this.pep = pep;
		this.perfil = perfil;
		this.riesgo = riesgo;
		this.modalidad = modalidad;
		this.terror = terror;
		this.sexo = sexo;
		this.obligado = obligado;
		this.vencimientoObl = vencimientoObl;
		this.vencimientoPep = vencimientoPep;
		this.vencimientoTer = vencimientoTer;
		this.provincia = provincia;
		this.beneficiarioFinal = beneficiarioFinal;
		this.email1 = email1;
		this.digital = digital;
		this.mandar = mandar;
		this.acred = acred;
		this.apode = apode;
		this.poder = poder;
		this.apodeSocio1 = apodeSocio1;
		this.apodeSocio2 = apodeSocio2;
		this.apodeSocio3 = apodeSocio3;
		this.pais = pais;
		this.localidadCont = localidadCont;
		this.provinciaCont = provinciaCont;
		this.paisCont = paisCont;
		this.celular = celular;
		this.lugar = lugar;
		this.vencimientoRUPP = vencimientoRUPP;
		this.domGt1 = domGt1;
		this.tipoGt1 = tipoGt1;
		this.m1 = m1;
		this.montoGt1 = montoGt1;
		this.vencimientoGt1 = vencimientoGt1;
		this.domGt2 = domGt2;
		this.tipoGt2 = tipoGt2;
		this.m2 = m2;
		this.montoGt2 = montoGt2;
		this.vencimientoGt2 = vencimientoGt2;
		this.domGt3 = domGt3;
		this.tipoGt3 = tipoGt3;
		this.m3 = m3;
		this.montoGt3 = montoGt3;
		this.vencimientoGt3 = vencimientoGt3;
		this.acti1 = acti1;
		this.acti2 = acti2;
		this.propo = propo;
		this.canal = canal;
		this.fechaVencimientoTope = fechaVencimientoTope;

	}

	public Integer getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(Integer numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public String getApellidoNombre() {
		return apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Date getFechaNacimiento_2() {
		return fechaNacimiento_2;
	}

	public void setFechaNacimiento_2(Date fechaNacimiento_2) {
		this.fechaNacimiento_2 = fechaNacimiento_2;
	}

	public Date getFechaIngreso_2() {
		return fechaIngreso_2;
	}

	public void setFechaIngreso_2(Date fechaIngreso_2) {
		this.fechaIngreso_2 = fechaIngreso_2;
	}

	public Date getFechaEgreso_2() {
		return fechaEgreso_2;
	}

	public void setFechaEgreso_2(Date fechaEgreso_2) {
		this.fechaEgreso_2 = fechaEgreso_2;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getCenso() {
		return censo;
	}

	public void setCenso(Date censo) {
		this.censo = censo;
	}

	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	public String getApellidoPadre() {
		return apellidoPadre;
	}

	public void setApellidoPadre(String apellidoPadre) {
		this.apellidoPadre = apellidoPadre;
	}

	public String getNombrePadre() {
		return nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public String getVivePadre() {
		return vivePadre;
	}

	public void setVivePadre(String vivePadre) {
		this.vivePadre = vivePadre;
	}

	public String getApellidoMadre() {
		return apellidoMadre;
	}

	public void setApellidoMadre(String apellidoMadre) {
		this.apellidoMadre = apellidoMadre;
	}

	public String getNombreMadre() {
		return nombreMadre;
	}

	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}

	public String getViveMadre() {
		return viveMadre;
	}

	public void setViveMadre(String viveMadre) {
		this.viveMadre = viveMadre;
	}

	public String getApellidoBeneficiario() {
		return apellidoBeneficiario;
	}

	public void setApellidoBeneficiario(String apellidoBeneficiario) {
		this.apellidoBeneficiario = apellidoBeneficiario;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getCarencia() {
		return carencia;
	}

	public void setCarencia(String carencia) {
		this.carencia = carencia;
	}

	public String getApellidoCasado() {
		return apellidoCasado;
	}

	public void setApellidoCasado(String apellidoCasado) {
		this.apellidoCasado = apellidoCasado;
	}

	public String getTipper() {
		return tipper;
	}

	public void setTipper(String tipper) {
		this.tipper = tipper;
	}

	public String getConjunta() {
		return conjunta;
	}

	public void setConjunta(String conjunta) {
		this.conjunta = conjunta;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getCompleto() {
		return completo;
	}

	public void setCompleto(String completo) {
		this.completo = completo;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getDomicilioContractual() {
		return domicilioContractual;
	}

	public void setDomicilioContractual(String domicilioContractual) {
		this.domicilioContractual = domicilioContractual;
	}

	public String getListado() {
		return listado;
	}

	public void setListado(String listado) {
		this.listado = listado;
	}

	public String getGarantias() {
		return garantias;
	}

	public void setGarantias(String garantias) {
		this.garantias = garantias;
	}

	public String getConstanciaInscripto() {
		return constanciaInscripto;
	}

	public void setConstanciaInscripto(String constanciaInscripto) {
		this.constanciaInscripto = constanciaInscripto;
	}

	public String getDdjj_iibb() {
		return ddjj_iibb;
	}

	public void setDdjj_iibb(String ddjj_iibb) {
		this.ddjj_iibb = ddjj_iibb;
	}

	public String getDdjj_ganancias() {
		return ddjj_ganancias;
	}

	public void setDdjj_ganancias(String ddjj_ganancias) {
		this.ddjj_ganancias = ddjj_ganancias;
	}

	public String getDdjj_bienesPersonales() {
		return ddjj_bienesPersonales;
	}

	public void setDdjj_bienesPersonales(String ddjj_bienesPersonales) {
		this.ddjj_bienesPersonales = ddjj_bienesPersonales;
	}

	public String getPagoMonotributo() {
		return pagoMonotributo;
	}

	public void setPagoMonotributo(String pagoMonotributo) {
		this.pagoMonotributo = pagoMonotributo;
	}

	public String getPagoIVA() {
		return pagoIVA;
	}

	public void setPagoIVA(String pagoIVA) {
		this.pagoIVA = pagoIVA;
	}

	public String getPagoIIBB() {
		return pagoIIBB;
	}

	public void setPagoIIBB(String pagoIIBB) {
		this.pagoIIBB = pagoIIBB;
	}

	public String getReciboSueldo() {
		return reciboSueldo;
	}

	public void setReciboSueldo(String reciboSueldo) {
		this.reciboSueldo = reciboSueldo;
	}

	public String getFotocopiaDNI() {
		return fotocopiaDNI;
	}

	public void setFotocopiaDNI(String fotocopiaDNI) {
		this.fotocopiaDNI = fotocopiaDNI;
	}

	public String getFotocopiaImpuesto() {
		return fotocopiaImpuesto;
	}

	public void setFotocopiaImpuesto(String fotocopiaImpuesto) {
		this.fotocopiaImpuesto = fotocopiaImpuesto;
	}

	public String getLiciFondo() {
		return liciFondo;
	}

	public void setLiciFondo(String liciFondo) {
		this.liciFondo = liciFondo;
	}

	public String getPep() {
		return pep;
	}

	public void setPep(String pep) {
		this.pep = pep;
	}

	public BigDecimal getPerfil() {
		return perfil;
	}

	public void setPerfil(BigDecimal perfil) {
		this.perfil = perfil;
	}

	public String getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getTerror() {
		return terror;
	}

	public void setTerror(String terror) {
		this.terror = terror;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getObligado() {
		return obligado;
	}

	public void setObligado(String obligado) {
		this.obligado = obligado;
	}

	public Date getVencimientoObl() {
		return vencimientoObl;
	}

	public void setVencimientoObl(Date vencimientoObl) {
		this.vencimientoObl = vencimientoObl;
	}

	public Date getVencimientoPep() {
		return vencimientoPep;
	}

	public void setVencimientoPep(Date vencimientoPep) {
		this.vencimientoPep = vencimientoPep;
	}

	public Date getVencimientoTer() {
		return vencimientoTer;
	}

	public void setVencimientoTer(Date vencimientoTer) {
		this.vencimientoTer = vencimientoTer;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getBeneficiarioFinal() {
		return beneficiarioFinal;
	}

	public void setBeneficiarioFinal(String beneficiarioFinal) {
		this.beneficiarioFinal = beneficiarioFinal;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getDigital() {
		return digital;
	}

	public void setDigital(String digital) {
		this.digital = digital;
	}

	public String getMandar() {
		return mandar;
	}

	public void setMandar(String mandar) {
		this.mandar = mandar;
	}

	public String getAcred() {
		return acred;
	}

	public void setAcred(String acred) {
		this.acred = acred;
	}

	public String getApode() {
		return apode;
	}

	public void setApode(String apode) {
		this.apode = apode;
	}

	public String getPoder() {
		return poder;
	}

	public void setPoder(String poder) {
		this.poder = poder;
	}

	public Integer getApodeSocio1() {
		return apodeSocio1;
	}

	public void setApodeSocio1(Integer apodeSocio1) {
		this.apodeSocio1 = apodeSocio1;
	}

	public Integer getApodeSocio2() {
		return apodeSocio2;
	}

	public void setApodeSocio2(Integer apodeSocio2) {
		this.apodeSocio2 = apodeSocio2;
	}

	public Integer getApodeSocio3() {
		return apodeSocio3;
	}

	public void setApodeSocio3(Integer apodeSocio3) {
		this.apodeSocio3 = apodeSocio3;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getLocalidadCont() {
		return localidadCont;
	}

	public void setLocalidadCont(String localidadCont) {
		this.localidadCont = localidadCont;
	}

	public String getProvinciaCont() {
		return provinciaCont;
	}

	public void setProvinciaCont(String provinciaCont) {
		this.provinciaCont = provinciaCont;
	}

	public String getPaisCont() {
		return paisCont;
	}

	public void setPaisCont(String paisCont) {
		this.paisCont = paisCont;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getVencimientoRUPP() {
		return vencimientoRUPP;
	}

	public void setVencimientoRUPP(Date vencimientoRUPP) {
		this.vencimientoRUPP = vencimientoRUPP;
	}

	public String getDomGt1() {
		return domGt1;
	}

	public void setDomGt1(String domGt1) {
		this.domGt1 = domGt1;
	}

	public String getTipoGt1() {
		return tipoGt1;
	}

	public void setTipoGt1(String tipoGt1) {
		this.tipoGt1 = tipoGt1;
	}

	public String getM1() {
		return m1;
	}

	public void setM1(String m1) {
		this.m1 = m1;
	}

	public BigDecimal getMontoGt1() {
		return montoGt1;
	}

	public void setMontoGt1(BigDecimal montoGt1) {
		this.montoGt1 = montoGt1;
	}

	public Date getVencimientoGt1() {
		return vencimientoGt1;
	}

	public void setVencimientoGt1(Date vencimientoGt1) {
		this.vencimientoGt1 = vencimientoGt1;
	}

	public String getDomGt2() {
		return domGt2;
	}

	public void setDomGt2(String domGt2) {
		this.domGt2 = domGt2;
	}

	public String getTipoGt2() {
		return tipoGt2;
	}

	public void setTipoGt2(String tipoGt2) {
		this.tipoGt2 = tipoGt2;
	}

	public String getM2() {
		return m2;
	}

	public void setM2(String m2) {
		this.m2 = m2;
	}

	public BigDecimal getMontoGt2() {
		return montoGt2;
	}

	public void setMontoGt2(BigDecimal montoGt2) {
		this.montoGt2 = montoGt2;
	}

	public Date getVencimientoGt2() {
		return vencimientoGt2;
	}

	public void setVencimientoGt2(Date vencimientoGt2) {
		this.vencimientoGt2 = vencimientoGt2;
	}

	public String getDomGt3() {
		return domGt3;
	}

	public void setDomGt3(String domGt3) {
		this.domGt3 = domGt3;
	}

	public String getTipoGt3() {
		return tipoGt3;
	}

	public void setTipoGt3(String tipoGt3) {
		this.tipoGt3 = tipoGt3;
	}

	public String getM3() {
		return m3;
	}

	public void setM3(String m3) {
		this.m3 = m3;
	}

	public BigDecimal getMontoGt3() {
		return montoGt3;
	}

	public void setMontoGt3(BigDecimal montoGt3) {
		this.montoGt3 = montoGt3;
	}

	public Date getVencimientoGt3() {
		return vencimientoGt3;
	}

	public void setVencimientoGt3(Date vencimientoGt3) {
		this.vencimientoGt3 = vencimientoGt3;
	}

	public String getActi1() {
		return acti1;
	}

	public void setActi1(String acti1) {
		this.acti1 = acti1;
	}

	public String getActi2() {
		return acti2;
	}

	public void setActi2(String acti2) {
		this.acti2 = acti2;
	}

	public String getPropo() {
		return propo;
	}

	public void setPropo(String propo) {
		this.propo = propo;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public Date getFechaVencimientoTope() {
		return fechaVencimientoTope;
	}

	public void setFechaVencimientoTope(Date fechaVencimientoTope) {
		this.fechaVencimientoTope = fechaVencimientoTope;
	}

}
