package com.ufps.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ufps.springboot.form.app.entities.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	
	private List<Pais> lista; 
	
	public PaisServiceImpl() {
		 this.lista = Arrays.asList(new Pais(1,"ES","España"),
				 new Pais(2,"MX","Mexico"),
				 new Pais(3,"CO","Colombia"),
				 new Pais(4,"VE","Venezuela"),
				 new Pais(5,"AR","Argentina"));
		
	}

	@Override
	public List<Pais> listar() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado=null;
		for (Pais pais : this.lista) {
			if(id==pais.getId()) {
				resultado=pais;
				break;
			}
			
		}
		return resultado;
	}

}
