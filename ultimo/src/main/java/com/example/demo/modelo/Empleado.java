package com.example.demo.modelo;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "sexo", length = 10)
    private String sexo;
    
    @Column(name = "numero_documento", length = 20)
    private String numeroDocumento;
    
    @Column(name = "lugar_expedicion_documento", length = 100)
    private String lugarExpedicionDocumento;
    
    @Column(name = "fecha_contratacion")
    private Date fechaContratacion;
    
    @Column(name = "cargo", length = 100)
    private String cargo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getLugarExpedicionDocumento() {
		return lugarExpedicionDocumento;
	}

	public void setLugarExpedicionDocumento(String lugarExpedicionDocumento) {
		this.lugarExpedicionDocumento = lugarExpedicionDocumento;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

   
}