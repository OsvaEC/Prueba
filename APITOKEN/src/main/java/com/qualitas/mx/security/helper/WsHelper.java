package com.qualitas.mx.security.helper;

import com.qualitas.mx.security.model.UsuarioModel;
import com.qualitas.mx.security.model.ws.Usuario;

public class WsHelper {
	
	
	
	public static UsuarioModel buildUser(Usuario entry, String password) {
		UsuarioModel user = new UsuarioModel();
		user.setUsername(entry.getUsername().toUpperCase());
		user.setPassword(password);
		user.setEsactivo(1);
		user.setAplicacion(entry.getAplicacion().toUpperCase());
		return user;
	}
	
	public static String validUser(Usuario entry) {
		String response="";
		StringBuilder error = new StringBuilder();
		if(entry!=null) {
			if(entry.getUsername() == null || entry.getUsername().trim().isEmpty()) {
				error.append("username, ");
			}
			if(entry.getUsername()!=null && entry.getUsername().length()>20) {
				error.append("username maximo 20 caracteres, ");
			}
			if(entry.getPassword() == null || entry.getPassword().trim().isEmpty()) {
				error.append("password, ");
			}
			if(entry.getPassword()!=null && entry.getPassword().length()>500) {
				error.append("password maximo 500 caracteres, ");
			}
			if(entry.getAplicacion() == null || entry.getAplicacion().trim().isEmpty()) {
				error.append("aplicacion, ");
			}
			if(entry.getAplicacion()!=null && entry.getAplicacion().length()>20) {
				error.append("aplicacion maximo 500 caracteres, ");
			}
		}else {
			error.append("Todos los campos son requeridos, ");
		}
		if(error.length()>0) {
			response = "Verificar datos: "+error.substring(0, error.length()-2);
		}else {
			response = "OK";
		}
		return response;
	}
}
