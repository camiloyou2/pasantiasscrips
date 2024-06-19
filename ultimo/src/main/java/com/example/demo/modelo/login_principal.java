package com.example.demo.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="FUNCIONALIDAD")
public class login_principal {
	
	@Id
	@Column(name ="FUNC_ID")

	String 	FUNC_ID;

	@Column(name ="FUNC_NOMBRE")
	String FUNC_NOMBRE;

	@Column(name ="FUNC_FECHACAMBIO")
	Date FUNC_FECHACAMBIO;

	@Column(name ="FUNC_REGISTRADOPOR")
	String FUNC_REGISTRADOPOR;

	@Column(name ="APLI_ID")
	int APLI_ID;

	public login_principal() {
		super();
	}

	public String getFUNC_ID() {
		return FUNC_ID;
	}

	public void setFUNC_ID(String fUNC_ID) {
		FUNC_ID = fUNC_ID;
	}

	public String getFUNC_NOMBRE() {
		return FUNC_NOMBRE;
	}

	public void setFUNC_NOMBRE(String fUNC_NOMBRE) {
		FUNC_NOMBRE = fUNC_NOMBRE;
	}

	public Date getFUNC_FECHACAMBIO() {
		return FUNC_FECHACAMBIO;
	}

	public void setFUNC_FECHACAMBIO(Date fUNC_FECHACAMBIO) {
		FUNC_FECHACAMBIO = fUNC_FECHACAMBIO;
	}

	public String getFUNC_REGISTRADOPOR() {
		return FUNC_REGISTRADOPOR;
	}

	public void setFUNC_REGISTRADOPOR(String fUNC_REGISTRADOPOR) {
		FUNC_REGISTRADOPOR = fUNC_REGISTRADOPOR;
	}

	public int getAPLI_ID() {
		return APLI_ID;
	}

	public void setAPLI_ID(int aPLI_ID) {
		APLI_ID = aPLI_ID;
	}
	
	
}
