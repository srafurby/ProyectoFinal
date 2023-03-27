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
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ticket;

	private String pasajero;

	private Long id_tren;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_horario", insertable = true, updatable = false)
	private Horario id_horario;

	private int precio;

	public Ticket() {
	}

	public Ticket(Long id_ticket, String pasajero, Long id_tren, Horario id_horario, int precio) {
		super();
		this.id_ticket = id_ticket;
		this.pasajero = pasajero;
		this.id_tren = id_tren;
		this.id_horario = id_horario;
		this.precio = precio;
	}

	public Long getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(Long id_ticket) {
		this.id_ticket = id_ticket;
	}

	public String getPasajero() {
		return pasajero;
	}

	public void setPasajero(String pasajero) {
		this.pasajero = pasajero;
	}

	public Long getId_tren() {
		return id_tren;
	}

	public void setId_tren(Long id_tren) {
		this.id_tren = id_tren;
	}

	public Horario getId_horario() {
		return id_horario;
	}

	public void setId_horario(Horario id_horario) {
		this.id_horario = id_horario;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
