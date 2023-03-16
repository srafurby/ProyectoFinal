package com.proyectoFinal.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoFinal.Entidades.Estacion;
import com.proyectoFinal.Entidades.Horario;
import com.proyectoFinal.Entidades.Pasajero;
import com.proyectoFinal.Entidades.Tren;
import com.proyectoFinal.Interfaces.EstacionRepository;
import com.proyectoFinal.Interfaces.HorarioRepository;
import com.proyectoFinal.Interfaces.PasajerosRepository;
import com.proyectoFinal.Interfaces.TrenRepository;
import com.proyectoFinal.Services.EstacionService;
import com.proyectoFinal.Services.HorarioService;
import com.proyectoFinal.Services.PasajeroService;
import com.proyectoFinal.Services.TrenService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	private TrenRepository trenRepository;
	@Autowired
	private EstacionRepository estacionRepository;
	@Autowired
	private HorarioRepository horarioRepository;
	@Autowired
	private PasajerosRepository pasajerosRepository;
	@Autowired
	private TrenService trenService;
	@Autowired
	private EstacionService estacionService;
	@Autowired
	private HorarioService horarioService;
	@Autowired
	private PasajeroService pasajeroService;

	@GetMapping("/admin")
	public String mostrarPaginaInicio(Model model, HttpSession session) {

		List<Tren> trenes = trenRepository.findAll();
		List<Estacion> estaciones = estacionRepository.findAll();
		List<Horario> horarios = horarioRepository.findAll();
		List<Pasajero> pasajeros = pasajerosRepository.findAll();

		model.addAttribute("trenes", trenes);
		model.addAttribute("estaciones", estaciones);
		model.addAttribute("horarios", horarios);
		model.addAttribute("pasajeros", pasajeros);

		return "admin";
	}

	@PostMapping("/addTrenes")
	public String addTren(Model model, @RequestParam(value = "asientos") int asientos,
			RedirectAttributes redirectAttributes) {
		Tren tren = new Tren();
		tren.setAsientos(asientos);
		tren.setEstacion((long) 1);

		trenRepository.save(tren);

		redirectAttributes.addFlashAttribute("message", "Train Added");

		return "redirect:/admin";
	}

	@PostMapping("/addEstaciones")
	public String addTren(Model model, @RequestParam(value = "nombreEstacion") String nombreEstacion,
			RedirectAttributes redirectAttributes) {
		Estacion estacion = new Estacion();
		estacion.setNombre(nombreEstacion);

		estacionRepository.save(estacion);

		redirectAttributes.addFlashAttribute("message", "Station Added");

		return "redirect:/admin";
	}

	@PostMapping("/addHorarios")
	public String addHorario(Model model, @RequestParam(value = "salida") double salida,
			@RequestParam(value = "llegada") double llegada, @RequestParam(value = "tren") long tren,
			@RequestParam(value = "estacion_salida") Estacion estacion_salida,
			@RequestParam(value = "estacion_llegada") Estacion estacion_llegada, RedirectAttributes redirectAttributes) {
		Horario horario = new Horario();
		horario.setSalida(salida);
		horario.setLlegada(llegada);
		horario.setTren(tren);
		horario.setEstacion_llegada(estacion_llegada);
		horario.setEstacion_salida(estacion_salida);

		horarioRepository.save(horario);

		redirectAttributes.addFlashAttribute("message", "Schedule Added");

		return "redirect:/admin";
	}

	@PostMapping("/admin/tren/{id}")
	public String eliminarTren(@PathVariable Long id) {
		trenService.eliminarTren(id);
		return "redirect:/admin";
	}

	@PostMapping("/admin/estacion/{id}")
	public String eliminarEstacion(@PathVariable Long id) {
		estacionService.eliminarEstacion(id);
		return "redirect:/admin";
	}
	@PostMapping("/admin/horario/{id}")
	public String eliminarHorario(@PathVariable Long id) {
		horarioService.eliminarHorario(id);
		return "redirect:/admin";
	}
	@PostMapping("/admin/pasajero/{id}")
	public String eliminarPasajero(@PathVariable String id) {
		pasajeroService.eliminarPasajero(id);
		return "redirect:/admin";
	}
	@PostMapping("/cerrarSesionAdmin")
	public String logout(HttpSession session) throws ServletException {
		session.removeAttribute("usuario");
		return "redirect:/";
	}
}