package com.example.demo.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="TIPOSOLICITUD")
public class TipoSolicitud {
@Id
@Column(name ="TIPO_ID")
int TIPO_ID;
@Column(name ="TIPO_NOMBRE")
String TIPO_NOMBRE;

@Column(name ="TIPO_REGISTRADOPOR")
String TIPO_REGISTRADOPOR;

@Column(name ="TIPO_FECHACAMBIO")
Date TIPO_FECHACAMBIO;

public TipoSolicitud() {
	super();
}

public int getTIPO_ID() {
	return TIPO_ID;
}

public void setTIPO_ID(int tIPO_ID) {
	TIPO_ID = tIPO_ID;
}

public String getTIPO_NOMBRE() {
	return TIPO_NOMBRE;
}

public void setTIPO_NOMBRE(String tIPO_NOMBRE) {
	TIPO_NOMBRE = tIPO_NOMBRE;
}

public String getTIPO_REGISTRADOPOR() {
	return TIPO_REGISTRADOPOR;
}

public void setTIPO_REGISTRADOPOR(String tIPO_REGISTRADOPOR) {
	TIPO_REGISTRADOPOR = tIPO_REGISTRADOPOR;
}

public Date getTIPO_FECHACAMBIO() {
	return TIPO_FECHACAMBIO;
}

public void setTIPO_FECHACAMBIO(Date tIPO_FECHACAMBIO) {
	TIPO_FECHACAMBIO = tIPO_FECHACAMBIO;
}


}
