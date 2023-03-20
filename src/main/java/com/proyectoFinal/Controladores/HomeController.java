package com.proyectoFinal.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectoFinal.Entidades.Estacion;
import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Interfaces.EstacionRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private EstacionRepository estacionRepository;

	@GetMapping("/")
	public String mostrarPaginaInicio(Model model) {

		// LISTA DE ESTACIONES
		List<Estacion> estaciones = estacionRepository.findAll();
		model.addAttribute("estaciones", estaciones);

		return "Index";
	}

	@GetMapping("/login_register")
	public String mostrarLoginRegister(HttpSession session, Model model) {
		Pasajero pasajero = (Pasajero) session.getAttribute("usuario");
		if (pasajero == null) {
			return "login_register";
		} else if (pasajero.getAdmin()==1) {
			return "redirect:admin";
		} else {
			return "redirect:user";
		}
	}
	
	@PostMapping("/busquedaHorario")
	public String busquedaHorario(@RequestParam("estacionSalida") Long idEstacionSalida,
			@RequestParam("estacionLlegada") Long idEstacionLlegada, Model model) {
		String url = "/buscadoHorario?estacionSalida=" + idEstacionSalida + "&estacionLlegada=" + idEstacionLlegada;
	    return "redirect:" + url;
	}
}
