package com.proyectoFinal.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tren")
public class Tren {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero_tren;

	private Long estacion;
	private int asientos;

	public Tren() {
	}

	public Tren(Long numero_tren, Long estacion, int asientos) {
		super();
		this.numero_tren = numero_tren;
		this.estacion = estacion;
		this.asientos = asientos;
	}

	public Long getNumero_tren() {
		return numero_tren;
	}

	public void setNumero_tren(Long numero_tren) {
		this.numero_tren = numero_tren;
	}

	public Long getEstacion() {
		return estacion;
	}

	public void setEstacion(Long estacion) {
		this.estacion = estacion;
	}

	public int getAsientos() {
		return asientos;
	}

	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}
}