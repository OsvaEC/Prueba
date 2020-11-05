package com.qualitas.mx.security.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Respuesta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private int codigo;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String mensaje;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String detalles;
	
	public Respuesta() {
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

}
