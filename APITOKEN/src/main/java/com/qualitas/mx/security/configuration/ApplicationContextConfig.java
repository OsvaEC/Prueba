package com.qualitas.mx.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({
	"com.qualitas.mx.security.configuration",
	"com.qualitas.mx.security.auth",
	"com.qualitas.mx.security.dao",
	"com.qualitas.mx.security.controllers"
	})
public class ApplicationContextConfig {

	@Bean(name="viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/jsp/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
}
