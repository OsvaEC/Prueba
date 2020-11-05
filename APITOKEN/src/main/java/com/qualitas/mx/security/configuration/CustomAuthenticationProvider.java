package com.qualitas.mx.security.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.qualitas.mx.security.dao.WsDaoImpl;
import com.qualitas.mx.security.model.Usuarios;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	WsDaoImpl dbOracle;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {

		String name = authentication.getName();
		String credentials = (String) authentication.getCredentials();
		System.out.println("******************credentials:"+credentials);
		Usuarios user = dbOracle.getUsuario(name.toUpperCase());
		if ((user != null && user.getUsername().equalsIgnoreCase(name))
				&& (passwordEncoder.matches(credentials, user.getPassword()))) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			System.out.println("******************Entro a usuarios de base");
			return new UsernamePasswordAuthenticationToken(user, credentials, grantedAuthorities);
		} else {
			throw new BadCredentialsException("Usuario o PWD  no valiido");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
