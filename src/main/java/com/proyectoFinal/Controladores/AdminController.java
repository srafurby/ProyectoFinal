package com.proyectoFinal.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@GetMapping("/admin")
	public String mostrarPaginaInicio(Model model, HttpSession session) {
		return "admin";
	}
}
