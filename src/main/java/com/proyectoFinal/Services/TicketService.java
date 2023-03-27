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
	
	//Metodo que busca un ticket por DNI
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
	//Metodo que comprueba que no haya tickets de la misma persona en el mismo horario
	public boolean ticketDuplicado(String Dni, Long id_horario) {

		List<Ticket> tickets = buscarTicketByDni(Dni);

		for (Ticket ticket : tickets) {
			if (ticket.getPasajero().equals(Dni) && ticket.getId_horario().getID_horario().equals(id_horario)) {
				return true;
			}
		}

		return false;
	}
	//Metodo para comprobar el numero de pasajeros que han comprado un ticket en un horario
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

	public void eliminarTicket(Long id) {
		ticketRepository.deleteById(id);
	}
}
