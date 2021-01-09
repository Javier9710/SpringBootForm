package com.ufps.springboot.form.app.entities;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.ufps.springboot.form.app.validation.IdRegex;
import com.ufps.springboot.form.app.validation.Requerido;

public class Usuario {

	// @NotEmpty
	// @Pattern(regexp="[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
	@IdRegex
	private String id;

	// @NotEmpty(message = "El nombre no puede ser vacio")
	private String nombre;

	// @NotEmpty(message = "El apellido no puede ser vacio")
	@Requerido
	private String apellido;

	// @NotEmpty
	@NotBlank
	@Size(min = 3, max = 8)
	private String username;

	@NotEmpty
	private String password;

	@Requerido
	@Email(message = "El correo es invalido")
	private String email;

	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;

	@NotNull
	@Future
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	// @Valid
	@NotNull
	private Pais pais;

	@NotNull
	private List<Rol> roles;

	private Boolean habilitar;

	@NotEmpty
	private String genero;

	private String valorSecreto;

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getHabilitar() {
		return habilitar;
	}

	public void setHabilitar(Boolean habilitar) {
		this.habilitar = habilitar;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getValorSecreto() {
		return valorSecreto;
	}

	public void setValorSecreto(String valorSecreto) {
		this.valorSecreto = valorSecreto;
	}
	
	

}
