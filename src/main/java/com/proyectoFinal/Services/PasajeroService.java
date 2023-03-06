package com.proyectoFinal.Services;

import java.time.LocalDate;

import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Interfaces.PasajerosRepository;

import jakarta.servlet.http.HttpSession;

public class PasajeroService {
	public void updatePasajero(String dni, String nombre, String apellidos, LocalDate f_nacimiento, PasajerosRepository pasajerosRepository, HttpSession session) {

		Pasajero pasajero = pasajerosRepository.findByDni(dni);
		
		pasajero.setNombre(nombre);
		pasajero.setApellidos(apellidos);
		pasajero.setF_nacimiento(f_nacimiento);
		
		pasajerosRepository.save(pasajero);
		
		session.setAttribute("usuario", pasajero);
	}
}
