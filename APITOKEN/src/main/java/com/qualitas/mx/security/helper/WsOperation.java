package com.qualitas.mx.security.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qualitas.mx.security.dao.WsDaoImpl;
import com.qualitas.mx.security.model.UsuarioModel;
import com.qualitas.mx.security.model.Usuarios;
import com.qualitas.mx.security.model.ws.Usuario;

public class WsOperation {
	
	private static final Logger log = LoggerFactory.getLogger(WsOperation.class);
	
	public static String insertUser(WsDaoImpl base,Usuario entry,String password,String userNameToken) {
		String response = "";
		String valid = WsHelper.validUser(entry);
		if(valid.equals("OK")) {
			Usuarios user = base.getUsuario(entry.getUsername().toUpperCase());
			Usuarios userToken = base.getUsuario(userNameToken.toUpperCase());
			if(user!=null) {
				response = "El usuario ya existe";
			}else if(userToken !=null && userToken.getAplicacion().trim().equals("ADMIN")){
				UsuarioModel usuario = WsHelper.buildUser(entry, password);
				base.insertUser(usuario);
				log.info("Id user:"+usuario.getId());
				response = "Usuario registrado";
			}else {
				response = "El token no es valido para registro de usuarios";
			}
		}else {
			response = valid;
		}
		return response;
	}
	
	
}
