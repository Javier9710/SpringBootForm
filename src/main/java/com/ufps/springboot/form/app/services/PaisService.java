package com.ufps.springboot.form.app.services;

import java.util.List;

import com.ufps.springboot.form.app.entities.Pais;

public interface PaisService {

	public List<Pais> listar();
	public Pais obtenerPorId(Integer id);
}
