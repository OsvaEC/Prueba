package com.qualitas.mx.security.model;

public class UsuarioModel {
	
	private int id;
	private String username;
	private String password;
	private String aplicacion;
	private int activo;
	private String fechaRegistro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public int getEsactivo() {
		return activo;
	}
	public void setEsactivo(int esactivo) {
		this.activo = esactivo;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
}
