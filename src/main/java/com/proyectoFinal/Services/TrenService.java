package com.proyectoFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Interfaces.TrenRepository;

@Service
public class TrenService {

	@Autowired
	private TrenRepository trenRepository;

	public void eliminarTren(Long id) {
		trenRepository.deleteById(id);
	}
}