package com.qualitas.mx.security.CustomPoint;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qualitas.mx.security.model.ResponseMessage;

public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint {
	 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    	
    	Date hoy = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String fecha= formatter.format(hoy);  
        
        Map<String, Object> result = new HashMap<>();
		result.put("tokenValido", false);
		result.put("user", null);
        ResponseMessage res = new ResponseMessage(fecha, HttpStatus.FORBIDDEN.value(), null,null ,"You don't have access to this resource", null, result);
        String body = new ObjectMapper().writeValueAsString(res);
        response.getWriter().write(body);
		response.setContentType("application/json");
		response.setStatus(403);
		response.flushBuffer();
        
        /*Map<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp",fecha);
		body.put("status", 403);
		body.put("user", "unknown");
		body.put("message", "You don't have access to this resource");
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(403);
		response.setContentType("application/json");*/
    }
    
    

//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//	}
}
