package com.qualitas.mx.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qualitas.mx.security.model.ResponseMessage;
import com.qualitas.mx.security.model.ws.Usuario;

@Component
@RequestMapping("/api/resource")
public interface WsInterface {
	
	@PostMapping("/validarToken")
	public ResponseEntity<ResponseMessage> validarToken();
	
	@PostMapping("/insertUser")
	public ResponseEntity<ResponseMessage> insertUser(@RequestBody Usuario usuario);
	
}
