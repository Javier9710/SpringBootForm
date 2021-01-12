package com.ufps.springboot.form.app.interceptores;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



@Component("tiempoTanscurridoInterceptor")
public class TiempoTanscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTanscurridoInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		
			throws Exception {
		if(request.getMethod().equalsIgnoreCase("psot")){
			return true;
		}
		
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");
		logger.info("Interceptando: " + handler);
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("TiempoInicio", tiempoInicio);
		
		Random random = new Random();
		Integer demora= random.nextInt(500);
		Thread.sleep(demora );
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if( handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("Es un methodo del controlador: "+ metodo.getMethod().getName());
			
			
		}
		long tiempoFinal = System.currentTimeMillis();
		long tiempoInicio = (Long)request.getAttribute("TiempoInicio");
		long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		if( handler instanceof HandlerMethod && modelAndView!=null) {
			modelAndView.addObject("tiempoTranscurrido",tiempoTranscurrido);
			
			
		}
		logger.info("Tiempo Transcurrido: " + tiempoTranscurrido+ " milisegundos");
		logger.info("Tiempo TranscurridoInterceptor: postHandle() entrando...");
		
		
	}

}
