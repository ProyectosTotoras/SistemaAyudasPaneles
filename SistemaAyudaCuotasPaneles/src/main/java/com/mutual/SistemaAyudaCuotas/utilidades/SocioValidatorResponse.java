package com.mutual.SistemaAyudaCuotas.utilidades;

public class SocioValidatorResponse {
	
	private boolean exito;
	private String mensaje;
	
	public boolean isExito() {
		return exito;
	}
	public void setExito(boolean exito) {
		this.exito = exito;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public SocioValidatorResponse() {
	}
	
	
	public SocioValidatorResponse(boolean exito, String mensaje) {
		super();
		this.exito = exito;
		this.mensaje = mensaje;
	}
	
	
}
