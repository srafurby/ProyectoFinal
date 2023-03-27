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

	//LIST OF STATIONS. SAME IN THE HOME CONTROLLER.
	@GetMapping("/tickets")
	public String mostrarPaginaTickets(Model model) { 
		List<Estacion> estaciones = estacionRepository.findAll(); //SEARCHES FOR ALL THE STATIONS IN THE DATABASE AND STORES THEM.
		model.addAttribute("estaciones", estaciones); //SEND STATIONS TO HTML
		return "tickets"; //RETURNS THE HTML
	}

	//TICKET SEARCH FROM TICKETS PAGE
	@PostMapping("/buscar")
	public String buscarHorarios(@RequestParam("estacionSalida") Long idEstacionSalida, 
			@RequestParam("estacionLlegada") Long idEstacionLlegada, Model model) { //PICK UP THE DEPARTURE AND ARRIVAL STATION IN A LONG
		horarios = horarioService.buscarHorarioIdEstacion(idEstacionSalida, idEstacionLlegada); //SEARCH DEPARTURE AND ARRIVAL STATION BY ID
		List<Estacion> estaciones = estacionRepository.findAll(); //FIND ALL STATIONS
		model.addAttribute("horarios", horarios);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("precio", ThreadLocalRandom.current().nextInt(10, 25 + 1)); //GENERATES A RANDOM PRICE

		return "tickets";
	}

	//TICKET SEARCH FROM HOME PAGE
	@GetMapping("/buscadoHorario")
	public String buscadoHorario(@RequestParam("estacionSalida") Long idEstacionSalida,
			@RequestParam("estacionLlegada") Long idEstacionLlegada, Model model) { //PICK UP THE DEPARTURE AND ARRIVAL STATION IN A LONG

		horarios = horarioService.buscarHorarioIdEstacion(idEstacionSalida, idEstacionLlegada); //SEARCH DEPARTURE AND ARRIVAL STATION BY ID
		List<Estacion> estaciones = estacionRepository.findAll(); //FIND ALL STATIONS
		model.addAttribute("horarios", horarios);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("precio", ThreadLocalRandom.current().nextInt(10, 25 + 1)); //GENERATES A RANDOM PRICE

		return "tickets";
	}

	//CALCULATE PRICE OF THE TICKET
	@PostMapping("/calculoTicket")
	public String calcularTicket(@RequestParam("id_horario") Long id_horario, @RequestParam("precio") int precio,
			Model model) { //WE RECEIVE THE SCHEDULE IN A LONG AND THE PRICE IN AN INT

		//WE SEARCH BY SCHEDULE ID WHICH DOES NOT EXIST IN THE DATABASE SO WE HAVE TO SET IT AS OPTIONAL
		Optional<Horario> horarioOptional = horarioRepository.findById(id_horario);

		Horario horario = horarioOptional.orElseThrow(); //IF EMPTY, THROWS AN ERROR

		Date hoy = new Date();
		Date manana = new Date(hoy.getTime() + (1000 * 60 * 60 * 24)); //ADD ONE DAY TO TODAY TO MAKE IT TOMORROW

		DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM"); //INBOUND AND OUTBOUND DATE LIKE jue 23 mar

		String fecha = dateFormat.format(hoy); //SAVE THE DATE IN A STRING
		String fecha1 = dateFormat.format(manana); //SAVE THE DATE IN A STRING

		List<Estacion> estaciones = estacionRepository.findAll(); 
		//WE SEND ALL THE LIST TO HTML
		model.addAttribute("horarios", horarios);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("horarios1", horario);
		model.addAttribute("fecha", fecha);
		model.addAttribute("fecha1", fecha1);
		model.addAttribute("precio", precio);

		return "tickets";
	}

	//CALCULATE THE FINAL PRICE
	@PostMapping("/calculoFinal")
	public String calculoFinal(HttpSession httpSession, @RequestParam("id_horario") Long id_horario,
			@RequestParam("precio") int precio, Model model,RedirectAttributes redirectAttributes) { //WE RECEIVE THE SCHEDULE IN A LONG AND THE PRICE IN AN INT
		
		Pasajero pasajero = (Pasajero) httpSession.getAttribute("usuario");
		
		if (pasajero == null) {
			redirectAttributes.addFlashAttribute("mensajeError", "You must be registered to be able to buy a ticket"); //ERROR, YOU HAVE TO BE REGISTERED
			return "redirect:/tickets#alerta";
		}else {
			if(pasajero.getAdmin()==1) {
				redirectAttributes.addFlashAttribute("mensajeError", "You can't do that as an administrator"); //ERROR, YOU CAN BUY A TICKET IF YOU ARE AN ADMIN
				return "redirect:/tickets#alerta";
			}else {
				//WE SEARCH BY SCHEDULE ID AND TRAINS ID WHICH DOES NOT EXIST IN THE DATABASE SO WE HAVE TO SET IT AS OPTIONAL
				Optional<Horario> horario_optional = horarioRepository.findById(id_horario);
				Horario horario = horario_optional.orElseThrow(); //IF EMPTY, THROWS AN ERROR
				
				Optional<Tren> tren_optional = trenRepository.findById(horario.getTren());
				Tren tren = tren_optional.orElseThrow(); //IF EMPTY, THROWS AN ERROR
				
				if(ticketService.ticketDuplicado(pasajero.getDni(), id_horario)) {
					redirectAttributes.addFlashAttribute("mensajeError", "You already bought a ticket for this Schedule!"); //ERROR, YOU CAN'T BUY 2 TICKETS FOR THE SAME TRAVEL
					return "redirect:/tickets#alerta";
				}else {
					int num_pasajeros = ticketService.num_pasajeros_tren(tren.getNumero_tren(), horario.getID_horario()); //FIND OUT HOW MANY PASSENGERS ARE INSIDE THE TRAIN
					
					if(tren.getAsientos()<=num_pasajeros) {
						redirectAttributes.addFlashAttribute("mensajeError", "The train for this Schedule is full. Select another time"); //ERROR, YOU CAN'T BUY A TICKET IF THE TRAIN IS FULL
						return "redirect:/tickets#alerta";
					}else {
						Ticket ticket = new Ticket(); //CREATE A NEW TICKET
						
						//COLLECT THE PASSENGER'S DNA, THE SCHEDULE, THE TRAIN NUMBER AND THE PRICE
						ticket.setPasajero(pasajero.getDni());
						ticket.setId_horario(horario);
						ticket.setId_tren(tren.getNumero_tren());
						ticket.setPrecio(precio);
						
						ticketRepository.save(ticket); //SAVE THE TICKET IN THE USER ACCOUNT

						redirectAttributes.addFlashAttribute("mensajeOK", "Purchased ticket, you can see and manage it in your user profile"); //MESSAGE PURCHASED TICKET
						return "redirect:/tickets#alerta";
					}
				}
			}
		}
	}
}
