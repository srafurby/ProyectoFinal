package com.proyectoFinal.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoFinal.Entidades.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
