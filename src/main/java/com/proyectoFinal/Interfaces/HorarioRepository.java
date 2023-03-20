package com.proyectoFinal.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoFinal.Entidades.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
}