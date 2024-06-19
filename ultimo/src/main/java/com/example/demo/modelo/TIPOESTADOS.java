package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TIPOESTADOS {

	@Id
	@Column(name ="TIES_DESCRIPCION")
	String TIES_DESCRIPCION;
	
	
	public String getTIES_DESCRIPCION() {
		return TIES_DESCRIPCION;
	}
	public void setTIES_DESCRIPCION(String tIES_DESCRIPCION) {
		TIES_DESCRIPCION = tIES_DESCRIPCION;
	}
	public TIPOESTADOS() {
		super();
	}
}
