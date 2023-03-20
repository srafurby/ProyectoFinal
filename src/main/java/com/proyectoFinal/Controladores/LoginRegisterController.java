package com.proyectoFinal.Controladores;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Interfaces.PasajerosRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {
	@Autowired
	private PasajerosRepository pasajerosRepository;

	@PostMapping("/pasajerosLogin")
	public String loginUsuario(@RequestParam String loginEmail, @RequestParam String loginPassword, HttpSession session,
			Model model) {

		Pasajero pasajero = pasajerosRepository.findByEmail(loginEmail);

		if (pasajero != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(loginPassword.getBytes());
				StringBuilder sb = new StringBuilder();
				for (byte b : messageDigest) {
					sb.append(String.format("%02x", b));
				}
				String passMD5 = sb.toString();

				if (passMD5.equals(pasajero.getPass())) {
					session.setAttribute("usuario", pasajero);
					if (pasajero.getAdmin()==1) {
						return "redirect:admin";
					} else {
						return "redirect:user";
					}
				} else {
					model.addAttribute("pasajeroError",
							"There is no user registered with that email or the password is incorrect");
					return "login_register";
				}
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}

		} else {
			model.addAttribute("pasajeroError",
					"There is no user registered with that email or the password is incorrect");
			return "login_register";
		}
	}

	@PostMapping("/pasajerosRegister")
	public String registrarUsuario(@ModelAttribute Pasajero pasajero, Model model) {
		// Verificar si ya existe un usuario con el mismo dni
		Pasajero pasajeroDni = pasajerosRepository.findByDni(pasajero.getDni());
		if (pasajeroDni != null) {
			model.addAttribute("dniError", "There is already a user with that ID");
			return "login_register";
		}

		// Verificar si ya existe un usuario con el mismo email
		Pasajero pasajeroEmail = pasajerosRepository.findByEmail(pasajero.getEmail());
		if (pasajeroEmail != null) {
			model.addAttribute("emailError", "There is already a user with that Email");
			return "login_register";
		}

		// Guardar el nuevo usuario en la base de datos
		pasajero.setAdmin(0);
		pasajerosRepository.save(pasajero);

		// Redirigir al usuario a la página de inicio
		return "redirect:/";
	}
}
