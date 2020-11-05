package com.qualitas.mx.security.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qualitas.mx.security.dao.WsDaoImpl;
import com.qualitas.mx.security.helper.WsOperation;
import com.qualitas.mx.security.model.ResponseMessage;
import com.qualitas.mx.security.model.ws.Usuario;


@RestController
public class WsController implements WsInterface{
	
	private static final Logger log = LoggerFactory.getLogger(WsController.class);
	
	@Autowired
	private WsDaoImpl base;
	
	@Autowired
	PasswordEncoder pwdEncoder;
	
	@Override
	public ResponseEntity<ResponseMessage> validarToken() {
		String hoy = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()); // HH(24), hh(12)
		Map<String, Object> result = new HashMap<>();
		try {
			log.info("WsController.validarToken Start con comentario");
			result.put("tokenValido", true);
			result.put("user", getUser());
			log.info("WsController.validarToken End");
			return new ResponseEntity<>(new ResponseMessage(hoy, HttpStatus.OK.value(), null ,null, "OK", null, result),HttpStatus.OK);
		} catch (Exception e) {
			log.info("WsController.validarToken Error");
			result.put("tokenValido", false);
			return new ResponseEntity<>(new ResponseMessage(hoy, HttpStatus.OK.value(), null ,null, "ERROR", e.getMessage(), result),HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<ResponseMessage> insertUser(@RequestBody Usuario usuario){
		String hoy = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()); // HH(24), hh(12)
		try {
			log.info("WsController.insertUser Start");
			String insert = WsOperation.insertUser(base, usuario, pwdEncoder.encode(usuario.getPassword()),getUser());
			log.info("WsController.insertUser End");
			return new ResponseEntity<>(new ResponseMessage(hoy, HttpStatus.OK.value(), null ,null, insert, null, null),HttpStatus.OK);
		}catch(Exception e) {
			log.info("WsController.insertUser Error");
			return new ResponseEntity<>(new ResponseMessage(hoy, HttpStatus.OK.value(), null ,null, "ERROR", e.getMessage(), e.getStackTrace()),HttpStatus.OK);
		}
	}
	
	public static String getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object p = authentication.getPrincipal();
		return p.toString();
	}
	
}
