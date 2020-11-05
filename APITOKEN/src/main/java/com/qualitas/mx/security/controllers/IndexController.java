package com.qualitas.mx.security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualitas.mx.security.model.Usuarios;

@Controller
public class IndexController {

	@GetMapping({"/"})
	public String index(Model model) {
		model.addAttribute("message", "APITOKEN");
		return "index";
	}
	
	@PostMapping("/test")
	@ResponseBody()
	public Usuarios test() {
		Usuarios u = obtenerDatosSesion();
		return u;
	}
	
	public Usuarios obtenerDatosSesion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Object principal = auth.getPrincipal();
	    Usuarios user = null;
	    if (principal instanceof Usuarios) {
	      user = ((Usuarios) principal);
	    }
	    return user;
	}
}
