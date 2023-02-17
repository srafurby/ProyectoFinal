package com.proyectoFinal.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectoFinal.Entidades.Estacion;
import com.proyectoFinal.Interfaces.EstacionRepository;

@Controller
public class HomeController {
	@Autowired
	private EstacionRepository estacionRepository;
	
	@GetMapping("/")
    public String mostrarPaginaInicio(Model model) {
		
		//LISTA DE ESTACIONES
		List<Estacion> estaciones = estacionRepository.findAll();
		model.addAttribute("estaciones", estaciones);
		
        return "Index";
    }
}
