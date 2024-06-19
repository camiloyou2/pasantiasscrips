package com.example.demo.modelo;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Table(name = "SOLICITUD_SERVICIOS")
public class Solicitud_servicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="SOSE_ID")
    private int SOSE_ID;
	@Column(name ="SERV_ID")
    private int SERV_ID;
	@Column(name ="SOSE_DESCRIPCION")
    private String SOSE_DESCRIPCION;
	@Column(name ="SOSE_REGISTRADOPOR")
    private String SOSE_REGISTRADOPOR;
	@Column(name ="SOSE_FECHACAMBIO")
    private Date SOSE_FECHACAMBIO;
	@Column(name ="SOSE_ATENDIDO")
    private String SOSE_ATENDIDO;
	@Column(name ="SOLI_ID")
    private int SOLI_ID;
	
	public int getSOSE_ID() {
		return SOSE_ID;
	}
	
	public void setSOSE_ID(int sOSE_ID) {
		SOSE_ID = sOSE_ID;
	}
	
	public int getSERV_ID() {
		return SERV_ID;
	}
	
	public void setSERV_ID(int sERV_ID) {
		SERV_ID = sERV_ID;
	}
	
	public String getSOSE_DESCRIPCION() {
		return SOSE_DESCRIPCION;
	}
	
	public void setSOSE_DESCRIPCION(String sOSE_DESCRIPCION) {
		SOSE_DESCRIPCION = sOSE_DESCRIPCION;
	}
	public String getSOSE_REGISTRADOPOR() {
		return SOSE_REGISTRADOPOR;
	}
	public void setSOSE_REGISTRADOPOR(String sOSE_REGISTRADOPOR) {
		SOSE_REGISTRADOPOR = sOSE_REGISTRADOPOR;
	}
	public Date getSOSE_FECHACAMBIO() {
		return SOSE_FECHACAMBIO;
	}
	public void setSOSE_FECHACAMBIO(Date sOSE_FECHACAMBIO) {
		SOSE_FECHACAMBIO = sOSE_FECHACAMBIO;
	}
	public String getSOSE_ATENDIDO() {
		return SOSE_ATENDIDO;
	}
	public void setSOSE_ATENDIDO(String sOSE_ATENDIDO) {
		SOSE_ATENDIDO = sOSE_ATENDIDO;
	}
	public int getSOLI_ID() {
		return SOLI_ID;
	}
	public void setSOLI_ID(int sOLI_ID) {
		SOLI_ID = sOLI_ID;
	}

	public Solicitud_servicios() {
		super();
	}
  
   
    
    

    // Constructor, getters y setters
}


