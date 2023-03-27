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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID_estacion;

	private String nombre;

	//CONSTRUCTOR
	public Estacion() {
	}

	public Estacion(Long iD_estacion, String nombre) {
		super();
		this.ID_estacion = iD_estacion;
		this.nombre = nombre;
	}

	//GETTER AND SETTER
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

}
