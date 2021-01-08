package com.ufps.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufps.springboot.form.app.entities.Rol;

@Service
public class RolServiceImpl implements RolService {

	private List<Rol> roles;

	public RolServiceImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Rol(1, "Adimistrador", "ROLE_ADMIN"));
		this.roles.add(new Rol(2, "Usuario", "ROLE_USER"));
		this.roles.add(new Rol(3, "Moderador", "ROLE_MODERADOR"));
	}

	@Override
	public List<Rol> listar() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public Rol obtenerPorId(Integer id) {
		Rol resultado = null;
		for (Rol rol : roles) {
			if (id == rol.getId()) {
				resultado = rol;
				break;
			}

		}
		return resultado;
	}

}
