package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class datospersonales {
	
    @Id
    @Column(name ="PEGE_ID")
    private String PEGE_ID;
    @Column(name ="PGFO_ARCHIVO")
    private byte[] PGFO_ARCHIVO;
    
    @Column(name ="PGFO_EXTENSION")
    private String PGFO_EXTENSION;
    
    
    
    @Column(name ="peng_primernombre")
    private String peng_primernombre;
    
    @Column(name ="peng_segundonombre")
    private String peng_segundonombre;
    
    @Column(name ="peng_primerapellido")
    private String peng_primerapellido;
    
    @Column(name ="peng_segundoapellido")
    private String peng_segundoapellido;
    
    
	public String getPEGE_ID() {
		return PEGE_ID;
	}
	public void setPEGE_ID(String pEGE_ID) {
		PEGE_ID = pEGE_ID;
	}
	public byte[] getPGFO_ARCHIVO() {
		return PGFO_ARCHIVO;
	}
	public void setPGFO_ARCHIVO(byte[] pGFO_ARCHIVO) {
		PGFO_ARCHIVO = pGFO_ARCHIVO;
	}
	public String getPGFO_EXTENSION() {
		return PGFO_EXTENSION;
	}
	public void setPGFO_EXTENSION(String pGFO_EXTENSION) {
		PGFO_EXTENSION = pGFO_EXTENSION;
	}
	public String getPeng_primernombre() {
		return peng_primernombre;
	}
	public void setPeng_primernombre(String peng_primernombre) {
		this.peng_primernombre = peng_primernombre;
	}
	public String getPeng_segundonombre() {
		return peng_segundonombre;
	}
	public void setPeng_segundonombre(String peng_segundonombre) {
		this.peng_segundonombre = peng_segundonombre;
	}
	public String getPeng_primerapellido() {
		return peng_primerapellido;
	}
	public void setPeng_primerapellido(String peng_primerapellido) {
		this.peng_primerapellido = peng_primerapellido;
	}
	public String getPeng_segundoapellido() {
		return peng_segundoapellido;
	}
	public void setPeng_segundoapellido(String peng_segundoapellido) {
		this.peng_segundoapellido = peng_segundoapellido;
	}

	
	

	    
}
