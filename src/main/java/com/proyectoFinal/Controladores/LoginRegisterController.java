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

	//LOGIN
	@PostMapping("/pasajerosLogin") 
	public String loginUsuario(@RequestParam String loginEmail, @RequestParam String loginPassword, HttpSession session,
			Model model) {

		Pasajero pasajero = pasajerosRepository.findByEmail(loginEmail); //LOOK FOR THE PASSENGER BY MAIL

		if (pasajero != null) { //SAVES THE PASSWORD IN THE DATABASE ENCRYPTED BY MD5
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(loginPassword.getBytes());
				StringBuilder sb = new StringBuilder();
				for (byte b : messageDigest) {
					sb.append(String.format("%02x", b));
				}
				String passMD5 = sb.toString(); 

				//COMPARE THE PASSWORD SET WITH THE PASSWORD IN THE DATABASE
				if (passMD5.equals(pasajero.getPass())) {
					session.setAttribute("usuario", pasajero); //I SAVE THE USER IN THE SESSION
					if (pasajero.getAdmin()==1) { //I CHECK IF IT IS ADMIN OR USER
						return "redirect:admin";
					} else {
						return "redirect:user";
					}
				} else { //ERROR EMAIL OR PASSWORD WRONG OR EMPTY
					model.addAttribute("pasajeroError",
							"There is no user registered with that email or the password is incorrect");
					return "login_register";
				}
			} catch (NoSuchAlgorithmException e) { 
				throw new RuntimeException(e);
			}

		} else { //ERROR EMAIL OR PASSWORD WRONG OR EMPTY
			model.addAttribute("pasajeroError",
					"There is no user registered with that email or the password is incorrect");
			return "login_register";
		}
	}

	//REGISTER
	@PostMapping("/pasajerosRegister")
	public String registrarUsuario(@ModelAttribute Pasajero pasajero, Model model) {
		//CHECK IF THERE IS ALREADY A USER WITH THE SAME ID
		Pasajero pasajeroDni = pasajerosRepository.findByDni(pasajero.getDni());
		if (pasajeroDni != null) {
			model.addAttribute("dniError", "There is already a user with that ID");
			return "login_register";
		}

		//VERIFY IF A USER WITH THE SAME EMAIL ALREADY EXIST
		Pasajero pasajeroEmail = pasajerosRepository.findByEmail(pasajero.getEmail());
		if (pasajeroEmail != null) {
			model.addAttribute("emailError", "There is already a user with that Email");
			return "login_register";
		}

		//SAVE THE NEW USER IN THE DATABASE
		pasajero.setAdmin(0);
		pasajerosRepository.save(pasajero);

		//REDIRECT USER TO HOME PAGE
		return "redirect:/";
	}
}
