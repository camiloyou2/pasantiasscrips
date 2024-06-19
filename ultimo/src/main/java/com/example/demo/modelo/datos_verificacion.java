package com.example.demo.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class datos_verificacion {
	
	@Column(name ="TIDG_ABREVIATURA")
	String TIDG_ABREVIATURA;
	@Id
	@Column(name ="PEGE_DOCUMENTOIDENTIDAD")
	String PEGE_DOCUMENTOIDENTIDAD;
	
	@Column(name ="PEGE_FECHAEXPEDICION")
	Date PEGE_FECHAEXPEDICION;
	public datos_verificacion() {
		super();
	}
	public String getTIDG_ABREVIATURA() {
		return TIDG_ABREVIATURA;
	}
	public void setTIDG_ABREVIATURA(String tIDG_ABREVIATURA) {
		TIDG_ABREVIATURA = tIDG_ABREVIATURA;
	}
	public String getPEGE_DOCUMENTOIDENTIDAD() {
		return PEGE_DOCUMENTOIDENTIDAD;
	}
	public void setPEGE_DOCUMENTOIDENTIDAD(String pEGE_DOCUMENTOIDENTIDAD) {
		PEGE_DOCUMENTOIDENTIDAD = pEGE_DOCUMENTOIDENTIDAD;
	}
	public Date getPEGE_FECHAEXPEDICION() {
		return PEGE_FECHAEXPEDICION;
	}
	public void setPEGE_FECHAEXPEDICION(Date pEGE_FECHAEXPEDICION) {
		PEGE_FECHAEXPEDICION = pEGE_FECHAEXPEDICION;
	}
	
}
