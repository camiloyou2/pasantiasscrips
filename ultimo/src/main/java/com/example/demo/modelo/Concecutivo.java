package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Concecutivo {

	@Id
	@Column(name ="concecutivo")
	String concecutivo;

	public String getConcecutivo() {
		return concecutivo;
	}

	public void setConcecutivo(String concecutivo) {
		this.concecutivo = concecutivo;
	}
	
	
	
}
