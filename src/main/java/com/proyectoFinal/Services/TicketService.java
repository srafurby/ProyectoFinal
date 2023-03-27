package com.proyectoFinal.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Entidades.Ticket;
import com.proyectoFinal.Interfaces.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	//METHOD THAT LOOKS FOR A TICKET BY DNA
	public List<Ticket> buscarTicketByDni(String dni) {

		List<Ticket> tickets = ticketRepository.findAll();

		List<Ticket> res = new ArrayList<Ticket>();

		for (Ticket ticket : tickets) {
			if (ticket.getPasajero().equals(dni)) {
				res.add(ticket);
			}
		}

		return res;
	}
	//METHOD THAT CHECKS THAT THERE ARE NO TICKETS FROM THE SAME PERSON AT THE SAME TIME
	public boolean ticketDuplicado(String Dni, Long id_horario) {

		List<Ticket> tickets = buscarTicketByDni(Dni);

		for (Ticket ticket : tickets) {
			if (ticket.getPasajero().equals(Dni) && ticket.getId_horario().getID_horario().equals(id_horario)) {
				return true;
			}
		}

		return false;
	}
	//METHOD TO CHECK THE NUMBER OF PASSENGERS WHO HAVE BOUGHT A TICKET IN A SCHEDULE
	public int num_pasajeros_tren(Long id_tren, Long id_horario) {
		int res = 0;

		List<Ticket> tickets = ticketRepository.findAll();

		for (Ticket ticket : tickets) {
			if (ticket.getId_horario().getID_horario().equals(id_horario) && ticket.getId_tren().equals(id_tren)) {
				res++;
			}
		}

		return res;
	}

	//METHOD TO DELETE TICKETS BY ID
	public void eliminarTicket(Long id) { 
		ticketRepository.deleteById(id);
	}
}
