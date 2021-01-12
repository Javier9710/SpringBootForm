package com.ufps.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.ufps.springboot.form.app.interceptores.TiempoTanscurridoInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("tiempoTanscurridoInterceptor")
	private HandlerInterceptor tiempoTranscurridoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tiempoTranscurridoInterceptor);
	}
	
	
	
}
