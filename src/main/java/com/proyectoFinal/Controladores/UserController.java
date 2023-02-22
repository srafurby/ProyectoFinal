package com.proyectoFinal.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/user")
	public String mostrarPaginaInicio(Model model) {
		return "user";
	}
}