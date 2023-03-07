package com.proyectoFinal.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectoFinal.Entidades.Estacion;
import com.proyectoFinal.Entidades.Horario;
import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Entidades.Tren;
import com.proyectoFinal.Interfaces.EstacionRepository;
import com.proyectoFinal.Interfaces.HorarioRepository;
import com.proyectoFinal.Interfaces.PasajerosRepository;
import com.proyectoFinal.Interfaces.TrenRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	private TrenRepository trenRepository;
	@Autowired
	private EstacionRepository estacionRepository;
	@Autowired
	private HorarioRepository horarioRepository;
	@Autowired
	private PasajerosRepository pasajerosRepository;

	@GetMapping("/admin")
	public String mostrarPaginaInicio(Model model, HttpSession session) {

		List<Tren> trenes = trenRepository.findAll();
		List<Estacion> estaciones = estacionRepository.findAll();
		List<Horario> horarios = horarioRepository.findAll();
		List<Pasajero> pasajeros = pasajerosRepository.findAll();

		model.addAttribute("trenes", trenes);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("horarios", horarios);
		model.addAttribute("pasajeros", pasajeros);

		return "admin";
	}
}