package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="SOLICITUD_SERVICIOS")
public class soliservicios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SOSE_ID")
	int SOSE_ID;
	@Column(name ="SOLI_ID")
	int SOLI_ID;
	@Column(name ="SERV_ID")
	int SERV_ID;
	@Column(name ="SOSE_DESCRIPCION")
	String SOSE_DESCRIPCION;
	@Column(name ="SOSE_REGISTRADOPOR")
	String SOSE_REGISTRADOPOR;
	@Column(name ="SOSE_FECHACAMBIO")
	String SOSE_FECHACAMBIO;
	
	@Column(name ="SOSE_ATENDIDO")
	String SOSE_ATENDIDO;
	
	
	public int getSOLI_ID() {
		return SOLI_ID;
	}
	public void setSOLI_ID(int sOLI_ID) {
		SOLI_ID = sOLI_ID;
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
	public String getSOSE_FECHACAMBIO() {
		return SOSE_FECHACAMBIO;
	}
	public void setSOSE_FECHACAMBIO(String sOSE_FECHACAMBIO) {
		SOSE_FECHACAMBIO = sOSE_FECHACAMBIO;
	}
	public int getSOSE_ID() {
		return SOSE_ID;
	}
	public void setSOSE_ID(int sOSE_ID) {
		SOSE_ID = sOSE_ID;
	}
	public String getSOSE_ATENDIDO() {
		return SOSE_ATENDIDO;
	}
	public void setSOSE_ATENDIDO(String sOSE_ATENDIDO) {
		SOSE_ATENDIDO = sOSE_ATENDIDO;
	}
	
	
	
}
