package com.ufps.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ufps.springboot.form.app.entities.Pais;
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
		binder.registerCustomEditor(Date.class, "fecha", new CustomDateEditor(dateFormat, true));

		binder.registerCustomEditor(String.class, "nombre", new MayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new MayusculaEditor());

	}
	
	/*
	 * @ModelAttribute("paises") public List<String> paises(){ return
	 * Arrays.asList("España","Mexico", "Colombia", "Argentina"); }
	 */

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return Arrays.asList(new Pais(1,"ES","España"),
							 new Pais(2,"MX","Mexico"),
							 new Pais(3,"CO","Colombia"),
							 new Pais(4,"AR","Argentina"));
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("ES", "España");
		paises.put("CO", "Colombia");
		paises.put("AR", "Argentina");
		paises.put("MX", "Mexico");
		return paises;

	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();
		usuario.setNombre("Jhon");
		usuario.setApellido("Delgado");
		usuario.setId("123.456.789-k");
		model.addAttribute("titulo", "Formulario de Usuario");
		model.addAttribute("usuario", usuario);
		return "form";

	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus sesion) {
		// validador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado del formulario");
		if (result.hasErrors()) {

			return "form";
		}

		model.addAttribute("usuario", usuario);
		sesion.setComplete();
		return "resultado";
	}

}
