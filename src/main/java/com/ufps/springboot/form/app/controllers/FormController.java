package com.ufps.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ufps.springboot.form.app.editors.PaisPropertyEditor;
import com.ufps.springboot.form.app.editors.RolEditor;
import com.ufps.springboot.form.app.entities.Pais;
import com.ufps.springboot.form.app.entities.Rol;
import com.ufps.springboot.form.app.entities.Usuario;
import com.ufps.springboot.form.app.services.PaisService;
import com.ufps.springboot.form.app.services.RolService;
import com.ufps.springboot.form.app.validation.UsuarioValidacion;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired RolEditor rolEditor;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RolService rolService;

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

		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Rol.class, "roles", rolEditor);

	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre","Mujer");
		
	}

	@ModelAttribute("listaRoles1")
	public List<Rol> listaRoles1() {
		return this.rolService.listar();
	}

	@ModelAttribute("paises")
	public List<String> paises() {

		return Arrays.asList("España", "Mexico", "Colombia", "Argentina");
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return Arrays.asList(new Pais(1, "ES", "España"), new Pais(2, "MX", "Mexico"), new Pais(3, "CO", "Colombia"),
				new Pais(4, "AR", "Argentina"));
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

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERADOR", "Moderador");
		return roles;

	}

	@ModelAttribute("listaRoles")
	public List<String> listaRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERADOR");

		return roles;
	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();
		usuario.setNombre("Jhon");
		usuario.setApellido("Delgado");
		usuario.setId("123.456.789-k");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Valor Secreto");
		usuario.setPais(new Pais(3,"CO","Colombia"));
		usuario.setRoles(Arrays.asList(new Rol(2, "Usuario", "ROLE_USER")));
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
