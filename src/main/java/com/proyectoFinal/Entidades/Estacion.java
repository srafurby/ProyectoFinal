package com.proyectoFinal.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estacion")
public class Estacion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID_estacion;

	private String nombre;
	private Integer horario;

	public Estacion() {
	}

	public Estacion(Long iD_estacion, String nombre, Integer horario) {
		super();
		ID_estacion = iD_estacion;
		this.nombre = nombre;
		this.horario = horario;
	}

	public Long getID_estacion() {
		return ID_estacion;
	}

	public void setID_estacion(Long iD_estacion) {
		ID_estacion = iD_estacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}
}
