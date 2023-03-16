package com.proyectoFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Interfaces.HorarioRepository;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;

	public void eliminarHorario(Long id) {
		horarioRepository.deleteById(id);
	}
}