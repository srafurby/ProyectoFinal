package com.proyectoFinal.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoFinal.Entidades.Tren;

public interface TrenRepository extends JpaRepository<Tren, Long> {
}