package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServiciosUniversidad")
public class ServiciosUniversidad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	 private String periodoContratacion;
	    private String nucleoTematico;
	    private Integer intensidadHoraria;

	    // Constructores, getters y setters


	  

	   

	    public String getPeriodoContratacion() {
	        return periodoContratacion;
	    }

	    public void setPeriodoContratacion(String periodoContratacion) {
	        this.periodoContratacion = periodoContratacion;
	    }

	    public String getNucleoTematico() {
	        return nucleoTematico;
	    }

	    public void setNucleoTematico(String nucleoTematico) {
	        this.nucleoTematico = nucleoTematico;
	    }

	    public Integer getIntensidadHoraria() {
	        return intensidadHoraria;
	    }

	    public void setIntensidadHoraria(Integer intensidadHoraria) {
	        this.intensidadHoraria = intensidadHoraria;
	    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
