package com.proyectoFinal.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoFinal.Entidades.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}