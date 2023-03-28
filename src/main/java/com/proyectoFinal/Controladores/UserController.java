package com.proyectoFinal.Controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Entidades.Ticket;
import com.proyectoFinal.Interfaces.PasajerosRepository;
import com.proyectoFinal.Services.PasajeroService;
import com.proyectoFinal.Services.TicketService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private PasajerosRepository pasajerosRepository;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private PasajeroService pasajeroService;

	@GetMapping("/user")
	public String mostrarPaginaInicio(Model model, HttpSession session) { // COLLECT THE INFORMATION OF MY USER SESSION
		Pasajero pasajero = (Pasajero) session.getAttribute("usuario"); // SHOW THE USER SESSION

		List<Ticket> tickets = ticketService.buscarTicketByDni(pasajero.getDni()); // SEARCH THE TICKETS THIS PASSENGER HAVE

		model.addAttribute("pasajero", pasajero);
		model.addAttribute("tickets", tickets);

		return "user";
	}

	@PostMapping("/user/{dni}") // CHANGE THE USER INFO
	public String cambiarDatosUsuario(@PathVariable String dni, @RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellidos") String apellidos,
			@RequestParam(value = "f_nacimiento") LocalDate f_nacimiento, RedirectAttributes redirectAttributes,
			HttpSession session) {

		PasajeroService pasajeroService = new PasajeroService();

		pasajeroService.updatePasajero(dni, nombre, apellidos, f_nacimiento, pasajerosRepository, session); // CHANGE THE INFO

		redirectAttributes.addFlashAttribute("message", "User updated successfully."); // MESSAGE FOR USER UPDATE
		return "redirect:/user"; // RETURN USER PAGE
	}

	@PostMapping("/cerrarSesion")
	public String logout(HttpSession session) throws ServletException { // LOGOUT OF YOUR SESSION
		session.removeAttribute("usuario");
		return "redirect:/";
	}

	@PostMapping("/user/cancelarTicket/{id}")
	public String cancelarTicket(@PathVariable Long id) {
		ticketService.eliminarTicket(id); // DELETE TICKET BY ID FROM TICKETSERVICE
		return "redirect:/user"; // RETURN USER PAGE
	}

	@PostMapping("/user/delete/{id}")
	public ResponseEntity<Object> eliminarPasajero(@PathVariable String id, HttpSession session) { // RECEIVE THE ID
		try {
			pasajeroService.eliminarPasajero(id);// DELETE THE USER IF IT HAS NO ERROR
			session.removeAttribute("usuario");// DELETE THE USER IN SESSION CACHE
			return ResponseEntity.ok().build();// RETURN TO AJAX PETITION OK RESPONSE
		} catch (DataIntegrityViolationException e) {// ERROR IF USER HAS PURCHASED TICKETS AND TRY TO DELETE (DATABASE RESTRICTION VIOLATED)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					"Your account can't be deleted if you have purchased tickets! If you want to delete anyway, please cancel all your tickets");// RETURN AJAX PETITION ERROR RESPONSE AND MESSAGE
		}
	}
}