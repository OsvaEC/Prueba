package com.qualitas.mx.security.auth.filters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualitas.mx.security.auth.service.JWTService;
import com.qualitas.mx.security.auth.service.JWTServiceImpl;
import com.qualitas.mx.security.model.ResponseMessage;
import com.qualitas.mx.security.model.UsuarioEntryJson;
import com.qualitas.mx.security.model.Usuarios;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		if(username != null && password !=null) {
			logger.info("Username -> form-data:" + username);
			logger.info("Password -> form-data:" + password);
		} else {
			UsuarioEntryJson user = null;
			try {
				user = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntryJson.class);
				username = user.getUsername();
				password = user.getPassword();
				logger.info("Username -> raw: " + username);
				logger.info("Password -> raw: " + password);		
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		username = username.trim();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = jwtService.create(authResult);
		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);
		Date hoy = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String fecha= formatter.format(hoy);
        Map<String, Object> result = new HashMap<>();
		result.put("tokenValido", true);
		result.put("user", ((Usuarios)authResult.getPrincipal()).getUsername());
		//result.put("user", authResult.getName());
		Usuarios user = (Usuarios) authResult.getPrincipal();
		user.setPassword(null);
		ResponseMessage res = new ResponseMessage(fecha, HttpStatus.OK.value(), user, token, "session started with successful=>"+((Usuarios)authResult.getPrincipal()).getUsername(), null, result);
        //ResponseMessage res = new ResponseMessage(fecha, HttpStatus.OK.value(), null, token, "session started with successful=>"+authResult.getName(), null, result);
		/*Map<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp",fecha);
		body.put("status", 200);
		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("message", String.format("session started with successful :) ->", ((User)authResult.getPrincipal()).getUsername()) );*/
		//response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		//response.setStatus(200);
        String body = new ObjectMapper().writeValueAsString(res);
        response.getWriter().write(body);
		response.setContentType("application/json");
		response.setStatus(200);
		response.flushBuffer();
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		Date hoy = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String fecha= formatter.format(hoy);
        Map<String, Object> result = new HashMap<>();
		result.put("tokenValido", false);
		result.put("user", null);
        ResponseMessage res = new ResponseMessage(fecha, HttpStatus.UNAUTHORIZED.value(), null,null ,"Error of authentication, username or password incorrect", null, result);
        String body = new ObjectMapper().writeValueAsString(res);
        response.getWriter().write(body);
		response.setContentType("application/json");
		response.setStatus(401);
		response.flushBuffer();
        /*Map<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp",fecha);
		body.put("status", 401);
		body.put("message", "Error of authentication, username or password incorrect :/");
		body.put("error", failed.getMessage());
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");*/
	}

}
