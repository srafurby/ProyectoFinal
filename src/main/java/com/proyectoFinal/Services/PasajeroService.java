package com.proyectoFinal.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Interfaces.PasajerosRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class PasajeroService {
	@Autowired
	private PasajerosRepository pasajeroRepository;
	
	//UPDATE INFO OF THE USER
	public void updatePasajero(String dni, String nombre, String apellidos, LocalDate f_nacimiento, PasajerosRepository pasajerosRepository, HttpSession session) {

		Pasajero pasajero = pasajerosRepository.findByDni(dni);
		
		pasajero.setNombre(nombre);
		pasajero.setApellidos(apellidos);
		pasajero.setF_nacimiento(f_nacimiento);
		
		pasajerosRepository.save(pasajero);
		
		session.setAttribute("usuario", pasajero);
	}
	public void eliminarPasajero(String id) { //METHOD TO DELETE PASSENGERS BY ID
		pasajeroRepository.deleteById(id);
	}
}
