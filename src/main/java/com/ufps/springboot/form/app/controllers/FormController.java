package com.ufps.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ufps.springboot.form.app.entities.Usuario;

@Controller
public class FormController {
	
	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new  Usuario();
		model.addAttribute("titulo", "Formulario de Usuario");
		model.addAttribute("user", usuario);
		return "form";
		
	}
	
	@PostMapping("/form")
	public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result ,Model model) {
		
		model.addAttribute("titulo", "Resultado del formulario");
		if(result.hasErrors()) {
			System.out.println("Entra 1");
			Map<String,String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "form";
		}
		System.out.println("Entra 2");
		model.addAttribute("usuario", usuario);
		
		return "resultado"; 
	}

}
