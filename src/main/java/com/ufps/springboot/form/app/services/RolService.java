package com.ufps.springboot.form.app.services;

import java.util.List;


import com.ufps.springboot.form.app.entities.Rol;

public interface RolService {
	
	public List<Rol> listar();
	public Rol obtenerPorId(Integer id);

}
