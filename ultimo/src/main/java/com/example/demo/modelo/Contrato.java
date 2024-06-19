package com.example.demo.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class Contrato {
	
	@Id
	@Column(name ="TIES_DESCRIPCION")
	String TIES_DESCRIPCION;
	
	
	public String getTIES_DESCRIPCION() {
		return TIES_DESCRIPCION;
	}
	public void setTIES_DESCRIPCION(String tIES_DESCRIPCION) {
		TIES_DESCRIPCION = tIES_DESCRIPCION;
	}
	public Contrato() {
		super();
	}
	
}
