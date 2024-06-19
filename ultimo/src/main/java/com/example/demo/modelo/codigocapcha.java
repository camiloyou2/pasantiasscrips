package com.example.demo.modelo;

public class codigocapcha {
String codigo;
byte[] imageBytes;
public codigocapcha(String codigo ) {
	super();
	this.codigo = codigo;
}
public codigocapcha( ) {
	
	super();
}
public String getCodigo() {
	return codigo;
}

public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public byte[] getImageBytes() {
	return imageBytes;
}
public void setImageBytes(byte[] imageBytes) {
	this.imageBytes = imageBytes;
}

}
