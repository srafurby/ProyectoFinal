package com.proyectoFinal.Controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoFinal.Entidades.Estacion;
import com.proyectoFinal.Entidades.Horario;
import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Entidades.Ticket;
import com.proyectoFinal.Entidades.Tren;
import com.proyectoFinal.Interfaces.EstacionRepository;
import com.proyectoFinal.Interfaces.HorarioRepository;
import com.proyectoFinal.Interfaces.TicketRepository;
import com.proyectoFinal.Interfaces.TrenRepository;
import com.proyectoFinal.Services.HorarioService;
import com.proyectoFinal.Services.TicketService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TicketController {
	@Autowired
	private EstacionRepository estacionRepository;
	@Autowired
	private HorarioRepository horarioRepository;
	@Autowired
	private HorarioService horarioService;
	@Autowired
	private TrenRepository trenRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketService ticketService;

	public List<Horario> horarios;

	@GetMapping("/tickets")
	public String mostrarPaginaTickets(Model model) {
		List<Estacion> estaciones = estacionRepository.findAll();
		model.addAttribute("estaciones", estaciones);
		return "tickets";
	}

	@PostMapping("/buscar")
	public String buscarHorarios(@RequestParam("estacionSalida") Long idEstacionSalida,
			@RequestParam("estacionLlegada") Long idEstacionLlegada, Model model) {

		horarios = horarioService.buscarHorarioIdEstacion(idEstacionSalida, idEstacionLlegada);
		List<Estacion> estaciones = estacionRepository.findAll();
		model.addAttribute("horarios", horarios);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("precio", ThreadLocalRandom.current().nextInt(10, 25 + 1));

		return "tickets";
	}

	@GetMapping("/buscadoHorario")
	public String buscadoHorario(@RequestParam("estacionSalida") Long idEstacionSalida,
			@RequestParam("estacionLlegada") Long idEstacionLlegada, Model model) {

		horarios = horarioService.buscarHorarioIdEstacion(idEstacionSalida, idEstacionLlegada);
		List<Estacion> estaciones = estacionRepository.findAll();
		model.addAttribute("horarios", horarios);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("precio", ThreadLocalRandom.current().nextInt(10, 25 + 1));

		return "tickets";
	}

	@PostMapping("/calculoTicket")
	public String calcularTicket(@RequestParam("id_horario") Long id_horario, @RequestParam("precio") int precio,
			Model model) {

		Optional<Horario> horarioOptional = horarioRepository.findById(id_horario);

		Horario horario = horarioOptional.orElseThrow();

		Date hoy = new Date();
		Date manana = new Date(hoy.getTime() + (1000 * 60 * 60 * 24));

		DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM");

		String fecha = dateFormat.format(hoy);
		String fecha1 = dateFormat.format(manana);

		List<Estacion> estaciones = estacionRepository.findAll();

		model.addAttribute("horarios", horarios);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("horarios1", horario);
		model.addAttribute("fecha", fecha);
		model.addAttribute("fecha1", fecha1);
		model.addAttribute("precio", precio);

		return "tickets";
	}

	@PostMapping("/calculoFinal")
	public String calculoFinal(HttpSession httpSession, @RequestParam("id_horario") Long id_horario,
			@RequestParam("precio") int precio, Model model,RedirectAttributes redirectAttributes) {
		
		Pasajero pasajero = (Pasajero) httpSession.getAttribute("usuario");
		
		if (pasajero == null) {
			redirectAttributes.addFlashAttribute("mensajeError", "You must be registered to be able to buy a ticket");
			return "redirect:/tickets#alerta";
		}else {
			if(pasajero.getAdmin()==1) {
				redirectAttributes.addFlashAttribute("mensajeError", "You can't do that as an administrator");
				return "redirect:/tickets#alerta";
			}else {
				Optional<Horario> horario_optional = horarioRepository.findById(id_horario);
				Horario horario = horario_optional.orElseThrow();
				
				Optional<Tren> tren_optional = trenRepository.findById(horario.getTren());
				Tren tren = tren_optional.orElseThrow();
				
				if(ticketService.ticketDuplicado(pasajero.getDni(), id_horario)) {
					redirectAttributes.addFlashAttribute("mensajeError", "You already bought a ticket for this Schedule!");
					return "redirect:/tickets#alerta";
				}else {
					int num_pasajeros = ticketService.num_pasajeros_tren(tren.getNumero_tren(), horario.getID_horario());
					
					if(tren.getAsientos()<=num_pasajeros) {
						redirectAttributes.addFlashAttribute("mensajeError", "The train for this Schedule is full. Select another time");
						return "redirect:/tickets#alerta";
					}else {
						Ticket ticket = new Ticket();
						
						ticket.setPasajero(pasajero.getDni());
						ticket.setId_horario(horario);
						ticket.setId_tren(tren.getNumero_tren());
						ticket.setPrecio(precio);
						
						ticketRepository.save(ticket);

						redirectAttributes.addFlashAttribute("mensajeOK", "Purchased ticket, you can see and manage it in your user profile");
						return "redirect:/tickets#alerta";
					}
				}
			}
		}
	}
}
