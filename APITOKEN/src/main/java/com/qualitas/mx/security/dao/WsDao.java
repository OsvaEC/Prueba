package com.qualitas.mx.security.dao;

import org.springframework.stereotype.Component;

import com.qualitas.mx.security.model.UsuarioModel;
import com.qualitas.mx.security.model.Usuarios;


@Component
public interface WsDao {
	
	public Usuarios getUsuario(String username);
	
	public void insertUser (UsuarioModel usuario);
	
	
	
}
