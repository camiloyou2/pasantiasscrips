package com.example.demo.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class visualizar_solicitud {
	 @Id    
	 @Column(name ="soli_fechasolicitud")
	 private String soli_fechasolicitud;
	 @Column(name ="rangoFecha")
	 private String rangoFecha;
	 @Column(name= "identificador")
	 private String identificador;
	 @Column(name ="serv_nombre")
	 private String serv_nombre;
	 @Column(name ="sose_atendido")
	 private String sose_atendido;
	 @Column(name ="soliid")
	 private String soliid;
	 @Column(name ="concecutivo")
	 private String concecutivo;
	 
	 
	 
	
	public String getSoliid() {
		return soliid;
	}
	public void setSoliid(String soliid) {
		this.soliid = soliid;
	}
	public String getSoli_fechasolicitud() {
		return soli_fechasolicitud;
	}
	public void setSoli_fechasolicitud(String soli_fechasolicitud) {
		this.soli_fechasolicitud = soli_fechasolicitud;
	}
	public String getRangoFecha() {
		return rangoFecha;
	}
	public void setRangoFecha(String soli_numero) {
		this.rangoFecha = soli_numero;
	}
	public String getServ_nombre() {
		return serv_nombre;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public void setServ_nombre(String serv_nombre) {
		this.serv_nombre = serv_nombre;
	}
	public String getSose_atendido() {
		return sose_atendido;
	}
	
	public String getConcecutivo() {
		return concecutivo;
	}
	public void setConcecutivo(String concecutivo) {
		this.concecutivo = concecutivo;
	}
	public void setSose_atendido(String sose_atendido) {
		this.sose_atendido = sose_atendido;
	}
	public visualizar_solicitud() {
		super();
	}
	 
	 
	 
}
