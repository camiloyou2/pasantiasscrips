package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class verificar_loging {
	@Id
	@Column(name ="EXISTE_REGISTRO")
	String EXISTE_REGISTRO;
	
	

	public verificar_loging() {
		super();
	}

	public String getEXISTE_REGISTRO() {
		return EXISTE_REGISTRO;
	}

	public void setEXISTE_REGISTRO(String eXISTE_REGISTRO) {
		EXISTE_REGISTRO = eXISTE_REGISTRO;
	}

	
}
