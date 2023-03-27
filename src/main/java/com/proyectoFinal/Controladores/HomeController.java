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

		//LIST OF STATIONS
		List<Estacion> estaciones = estacionRepository.findAll(); //SEARCHES FOR ALL THE STATIONS IN THE DATABASE AND STORES THEM.
		model.addAttribute("estaciones", estaciones); //SEND STATIONS TO HTML

		return "Index"; //RETURNS THE HTML
	}

	@GetMapping("/login_register") //IT TAKES YOU TO THE LOGIN/REGISTER
	public String mostrarLoginRegister(HttpSession session, Model model) { //SAVE THE SESSION FOR THE NEXT TIME
		Pasajero pasajero = (Pasajero) session.getAttribute("usuario");
		if (pasajero == null) { //KNOW IF YOU ARE ADMIN OR USER
			return "login_register";
		} else if (pasajero.getAdmin()==1) { 
			return "redirect:admin";
		} else {
			return "redirect:user";
		}
	}
	
	@PostMapping("/busquedaHorario") //IT IS USED TO COLLECT THE INFORMATION AND TAKE US TO THE TICKETS PAGE BY GIVING THE BUTTON
	public String busquedaHorario(@RequestParam("estacionSalida") Long idEstacionSalida,
			@RequestParam("estacionLlegada") Long idEstacionLlegada, Model model) { //LOOKS FOR THE DEPARTURE AND ARRIVAL STATION AND SAVES THEM
		String url = "/buscadoHorario?estacionSalida=" + idEstacionSalida + "&estacionLlegada=" + idEstacionLlegada;
	    return "redirect:" + url;
	}
}
