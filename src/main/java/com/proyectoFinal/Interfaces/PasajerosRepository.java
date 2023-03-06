package com.proyectoFinal.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoFinal.Entidades.Pasajero;

public interface PasajerosRepository extends JpaRepository<Pasajero, String> {
	Pasajero findByDni(String dni);

	Pasajero findByEmail(String email);

	Pasajero findByEmailAndPass(String email, String pass);
}
