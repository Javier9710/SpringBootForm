package com.ufps.springboot.form.app.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ufps.springboot.form.app.editors.MayusculaEditor;
import com.ufps.springboot.form.app.entities.Usuario;
import com.ufps.springboot.form.app.validation.UsuarioValidacion;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	
	@Autowired
	private UsuarioValidacion validador;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"fecha", new CustomDateEditor(dateFormat, true));
		
		
		binder.registerCustomEditor(String.class,"nombre", new MayusculaEditor());
		binder.registerCustomEditor(String.class,"apellido", new MayusculaEditor());
	
	}
	
	
	
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new  Usuario();
		usuario.setNombre("Jhon");
		usuario.setApellido("Delgado");
		usuario.setId("123.456.789-k");
		model.addAttribute("titulo", "Formulario de Usuario");
		model.addAttribute("usuario", usuario);
		return "form";
		
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result ,Model model, SessionStatus sesion) {
		//validador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado del formulario");
		if(result.hasErrors()) {

			return "form";
		}

		model.addAttribute("usuario", usuario);
		sesion.setComplete();
		return "resultado"; 
	}

}
