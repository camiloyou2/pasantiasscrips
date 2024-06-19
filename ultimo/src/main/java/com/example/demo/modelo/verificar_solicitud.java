package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class verificar_solicitud {
	@Id
	@Column(name ="SOLI_ID")
	String SOLI_ID;

	public String getSOLI_ID() {
		return SOLI_ID;
	}

	public void setSOLI_ID(String sOLI_ID) {
		SOLI_ID = sOLI_ID;
	}

	public verificar_solicitud() {
		super();
	}
	
	
	
	
}
