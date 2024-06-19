package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parametros")
public class Datosalario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "SUELDOMENSUAL")
    private Double SUELDOMENSUAL;

    @Column(name = "PRIMAANTIGUEDAD")
    private Double PRIMAANTIGUEDAD;

    @Column(name = "PRIMAALOJAMIENTO")
    private Double PRIMAALOJAMIENTO;

    @Column(name = "PRIMANAVIDAD")
    private Double PRIMANAVIDAD;

    @Column(name = "VACACIONES")
    private Double VACACIONES;

    @Column(name = "PRIMAVACACIONES")
    private Double PRIMAVACACIONES;

    @Column(name = "BONIFICACIONDICIEMBRE")
    private Double BONIFICACIONDICIEMBRE;

    @Column(name = "BONIFICACIONJULIO")
    private Double BONIFICACIONJULIO;

    @Column(name = "RETROACTIVO")
    private Double RETROACTIVO;

    @Column(name = "PRIMASEMESTRAL")
    private Double PRIMASEMESTRAL;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Double getSUELDOMENSUAL() {
		return SUELDOMENSUAL;
	}

	public void setSUELDOMENSUAL(Double sUELDOMENSUAL) {
		SUELDOMENSUAL = sUELDOMENSUAL;
	}

	public Double getPRIMAANTIGUEDAD() {
		return PRIMAANTIGUEDAD;
	}

	public void setPRIMAANTIGUEDAD(Double pRIMAANTIGUEDAD) {
		PRIMAANTIGUEDAD = pRIMAANTIGUEDAD;
	}

	public Double getPRIMAALOJAMIENTO() {
		return PRIMAALOJAMIENTO;
	}

	public void setPRIMAALOJAMIENTO(Double pRIMAALOJAMIENTO) {
		PRIMAALOJAMIENTO = pRIMAALOJAMIENTO;
	}

	public Double getPRIMANAVIDAD() {
		return PRIMANAVIDAD;
	}

	public void setPRIMANAVIDAD(Double pRIMANAVIDAD) {
		PRIMANAVIDAD = pRIMANAVIDAD;
	}

	public Double getVACACIONES() {
		return VACACIONES;
	}

	public void setVACACIONES(Double vACACIONES) {
		VACACIONES = vACACIONES;
	}

	public Double getPRIMAVACACIONES() {
		return PRIMAVACACIONES;
	}

	public void setPRIMAVACACIONES(Double pRIMAVACACIONES) {
		PRIMAVACACIONES = pRIMAVACACIONES;
	}

	public Double getBONIFICACIONDICIEMBRE() {
		return BONIFICACIONDICIEMBRE;
	}

	public void setBONIFICACIONDICIEMBRE(Double bONIFICACIONDICIEMBRE) {
		BONIFICACIONDICIEMBRE = bONIFICACIONDICIEMBRE;
	}

	public Double getBONIFICACIONJULIO() {
		return BONIFICACIONJULIO;
	}

	public void setBONIFICACIONJULIO(Double bONIFICACIONJULIO) {
		BONIFICACIONJULIO = bONIFICACIONJULIO;
	}

	public Double getRETROACTIVO() {
		return RETROACTIVO;
	}

	public void setRETROACTIVO(Double rETROACTIVO) {
		RETROACTIVO = rETROACTIVO;
	}

	public Double getPRIMASEMESTRAL() {
		return PRIMASEMESTRAL;
	}

	public void setPRIMASEMESTRAL(Double pRIMASEMESTRAL) {
		PRIMASEMESTRAL = pRIMASEMESTRAL;
	}

    
}
