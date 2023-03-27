package com.proyectoFinal.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID_horario;

	private double salida;
	private double llegada;
	private Long tren;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estacion_salida", insertable = true, updatable = false)
	private Estacion estacion_salida;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_estacion_llegada", insertable = true, updatable = false)
	private Estacion estacion_llegada;

	public Horario() {
	}

	public Horario(Long iD_horario, double salida, double llegada, Long tren, Estacion estacion_salida,
			Estacion estacion_llegada) {
		super();
		ID_horario = iD_horario;
		this.salida = salida;
		this.llegada = llegada;
		this.tren = tren;
		this.estacion_salida = estacion_salida;
		this.estacion_llegada = estacion_llegada;
	}

	public Long getID_horario() {
		return ID_horario;
	}

	public void setID_horario(Long iD_horario) {
		ID_horario = iD_horario;
	}

	public double getSalida() {
		return salida;
	}

	public void setSalida(double salida) {
		this.salida = salida;
	}

	public double getLlegada() {
		return llegada;
	}

	public void setLlegada(double llegada) {
		this.llegada = llegada;
	}

	public Long getTren() {
		return tren;
	}

	public void setTren(Long tren) {
		this.tren = tren;
	}

	public Estacion getEstacion_salida() {
		return estacion_salida;
	}

	public void setEstacion_salida(Estacion estacion_salida) {
		this.estacion_salida = estacion_salida;
	}

	public Estacion getEstacion_llegada() {
		return estacion_llegada;
	}

	public void setEstacion_llegada(Estacion estacion_llegada) {
		this.estacion_llegada = estacion_llegada;
	}

}