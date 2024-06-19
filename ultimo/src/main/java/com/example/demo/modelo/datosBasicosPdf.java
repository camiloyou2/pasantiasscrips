package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class datosBasicosPdf {
	 @Id    
	 @Column(name ="nombre")
	 private String nombre;
	 @Column(name ="tipodoc")
	 private String tipodoc;
	 @Column(name ="doc")
	 private String doc;
	 @Column(name ="expedicion")
	 private String expedicion;
	 @Column(name ="sexo")
	 private String sexo;
	 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipodoc() {
		return tipodoc;
	}
	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getExpedicion() {
		return expedicion;
	}
	public void setExpedicion(String expedicion) {
		this.expedicion = expedicion;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	 
	 
	 
}
