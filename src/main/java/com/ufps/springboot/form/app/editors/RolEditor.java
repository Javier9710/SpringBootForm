package com.ufps.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ufps.springboot.form.app.services.RolService;

@Component
public class RolEditor extends PropertyEditorSupport{
	
	@Autowired
	private RolService service;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			
			Integer id= Integer.parseInt(text);
			setValue(service.obtenerPorId(id));
			
		}catch (NumberFormatException e) {
			setValue(null);
		
		}
		
	}
	
	

}
