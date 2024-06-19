package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class VerificarCertificado {
	@Id
	@Column(name ="concecutivo")
	String concecutivo;
	@Id
	@Column(name ="identificador")
	String identificador;
	
	@Column(name ="tipo")
	String tipo;

	public String getConcecutivo() {
		return concecutivo;
	}

	public void setConcecutivo(String concecutivo) {
		this.concecutivo = concecutivo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
