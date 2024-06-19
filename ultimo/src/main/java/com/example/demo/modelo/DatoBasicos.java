package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class DatoBasicos {
	 @Id    
	 @Column(name ="nombre")
	 private String nombre;
	 @Column(name ="correo")
	 private String correo;
	 @Column(name= "doc")
	 private String doc;
	 
	 @Column(name= "pdfBytes")
	 private byte[] pdfBytes;
		
	 
	public byte[] getPdfBytes() {
		return pdfBytes;
	}
	public void setPdfBytes(byte[] pdfBytes) {
		this.pdfBytes = pdfBytes;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	 
	 
	 
}
