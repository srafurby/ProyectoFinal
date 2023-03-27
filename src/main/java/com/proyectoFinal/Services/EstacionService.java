package com.proyectoFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Interfaces.EstacionRepository;

@Service
public class EstacionService {

	@Autowired
	private EstacionRepository estacionRepository;

	public void eliminarEstacion(Long id) { //METHOD TO DELETE THE STATIONS BY ID
		estacionRepository.deleteById(id);
	}
}