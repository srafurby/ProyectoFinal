package com.proyectoFinal.Controladores;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Interfaces.PasajerosRepository;
import com.proyectoFinal.Services.PasajeroService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private PasajerosRepository pasajerosRepository;

	@GetMapping("/user")
	public String mostrarPaginaInicio(Model model, HttpSession session) {
		Pasajero pasajero = (Pasajero) session.getAttribute("usuario");

		model.addAttribute("pasajero", pasajero);

		return "user";
	}

	@PostMapping("/user/{dni}")
	public String cambiarDatosUsuario(@PathVariable String dni, @RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellidos") String apellidos,
			@RequestParam(value = "f_nacimiento") LocalDate f_nacimiento, RedirectAttributes redirectAttributes,
			HttpSession session) {

		PasajeroService pasajeroService = new PasajeroService();

		pasajeroService.updatePasajero(dni, nombre, apellidos, f_nacimiento, pasajerosRepository, session);

		redirectAttributes.addFlashAttribute("message", "Usuario actualizado correctamente.");
		return "redirect:/user";
	}

	@PostMapping("/cerrarSesion")
	public String logout(HttpSession session) throws ServletException {
		session.removeAttribute("usuario");
		return "redirect:/";
	}
	
}