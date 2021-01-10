package com.ufps.springboot.form.app.entities;

import javax.validation.constraints.NotNull;

public class Rol {
	
	@NotNull
	private Integer id;
	private String nombre;
	private String rol;

	public Rol() {

	}

	public Rol(Integer id, String nombre, String rol) {

		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public boolean equals(Object obj) {
		Rol rol = (Rol)obj;
		return this.id != null && this.id.equals(rol.getId());
	}
	
	

}
