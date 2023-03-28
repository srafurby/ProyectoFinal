package com.proyectoFinal.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import jakarta.servlet.http.HttpServletRequest;
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

		// FIND ALL TRAINS, STATIONS, SCHEDULES AND PASSENGERS
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

	// ADD TRAINS, STATIONS, SCHEDULES
	@PostMapping("/addTrenes")
	public String addTren(Model model, @RequestParam(value = "asientos") int asientos,
			RedirectAttributes redirectAttributes) { // PASS THE VALUE OF SEATS TO AN INT
		Tren tren = new Tren(); // CREATE A NEW TRAIN
		tren.setAsientos(asientos); // CHANGE THE NUMBERS OF SEATS
		tren.setEstacion((long) 1);

		trenRepository.save(tren); // SAVE THE INFO CHANGED

		redirectAttributes.addFlashAttribute("message", "Train Added"); // MESSAGE OF TRAIN ADDED

		return "redirect:/admin"; // RETURN TO ADMIN PAGE
	}

	@PostMapping("/addEstaciones")
	public String addTren(Model model, @RequestParam(value = "nombreEstacion") String nombreEstacion,
			RedirectAttributes redirectAttributes) { // PASS THE VALUE OF STATION NAME TO AN STRING
		Estacion estacion = new Estacion(); // CREATE A NEW STATION
		estacion.setNombre(nombreEstacion); // CHANGE THE NAME OF THE STATION

		estacionRepository.save(estacion); // SAVE THE INFO CHANGED

		redirectAttributes.addFlashAttribute("message", "Station Added"); // MESSAGE OF STATION ADDED

		return "redirect:/admin"; // RETURN TO ADMIN
	}

	@PostMapping("/addHorarios")
	public String addHorario(Model model, @RequestParam(value = "salida") double salida,
			@RequestParam(value = "llegada") double llegada, @RequestParam(value = "tren") long tren,
			@RequestParam(value = "estacion_salida") Estacion estacion_salida,
			@RequestParam(value = "estacion_llegada") Estacion estacion_llegada,
			RedirectAttributes redirectAttributes) {
		// PASS THE VALUE OF DEPARTURE, ARRIVAL, DEPARTURE STATION AND ARRIVAL STATION
		// TO A DOUBLE AND A LONG.
		Horario horario = new Horario(); // CREATE A NEW SCHEDULE
		horario.setSalida(salida); // CHANGE THE TIME OF DEPARTURE
		horario.setLlegada(llegada); // CHANGE THE TIME OF ARRIVAL
		horario.setTren(tren); // CHANGE THE TRAIN NUMBER
		horario.setEstacion_llegada(estacion_llegada); // CHANGE THE ARRIVAL STATION
		horario.setEstacion_salida(estacion_salida); // CHANGE THE DEPARTURE STATION

		horarioRepository.save(horario); // SAVE THE INFO CHANGED

		redirectAttributes.addFlashAttribute("message", "Schedule Added"); // MESSAGE OF SCHEDULE ADDED

		return "redirect:/admin"; // RETURN TO ADMIN
	}

	// MODIFY TRAINS, STATIONS AND SCHEDULES
	@PostMapping("/modifyTrenes")
	public String modifyTren(@RequestParam(value = "asientos") int asientos,
			@RequestParam(value = "idModifyTren") Long id, RedirectAttributes redirectAttributes) {
		Optional<Tren> trenOptional = trenRepository.findById(id);
		Tren tren = trenOptional.orElseThrow();

		tren.setAsientos(asientos);

		trenRepository.save(tren);

		redirectAttributes.addFlashAttribute("message", "Train Modified"); // MESSAGE OF SCHEDULE ADDED

		return "redirect:/admin"; // RETURN TO ADMIN
	}

	@PostMapping("/modifyHorarios")
	public String modifyEstacion(@RequestParam(value = "salida") double salida,
			@RequestParam(value = "llegada") double llegada, @RequestParam(value = "tren") long tren,
			@RequestParam(value = "estacion_salida") Estacion estacion_salida,
			@RequestParam(value = "estacion_llegada") Estacion estacion_llegada,
			@RequestParam(value = "idModifyHorario") Long id, RedirectAttributes redirectAttributes) {
		Optional<Horario> horarioOptional = horarioRepository.findById(id);
		Horario horario = horarioOptional.orElseThrow();

		horario.setSalida(salida);
		horario.setLlegada(llegada);
		horario.setEstacion_salida(estacion_salida);
		horario.setEstacion_llegada(estacion_llegada);
		horario.setTren(tren);

		horarioRepository.save(horario);

		redirectAttributes.addFlashAttribute("message", "Schedule Modified"); // MESSAGE OF SCHEDULE ADDED

		return "redirect:/admin"; // RETURN TO ADMIN
	}

	@PostMapping("/modifyEstaciones")
	public String modifyHorario(@RequestParam(value = "nombreEstacion") String nombre,
			@RequestParam(value = "idModifyEstacion") Long id, RedirectAttributes redirectAttributes) {
		Optional<Estacion> estacionOptional = estacionRepository.findById(id);
		Estacion estacion = estacionOptional.orElseThrow();

		estacion.setNombre(nombre);

		estacionRepository.save(estacion);

		redirectAttributes.addFlashAttribute("message", "Station Modified"); // MESSAGE OF SCHEDULE ADDED

		return "redirect:/admin"; // RETURN TO ADMIN
	}

	// DELETE TRAINS, STATIONS, SCHEDULES AND USERS
	@PostMapping("/admin/tren/{id}")
	public ResponseEntity<Object> eliminarTren(@PathVariable Long id) { // RECEIVE THE ID
		try {
			trenService.eliminarTren(id); // DELETE THE TRAIN IF IT HAS NO ERROR
			return ResponseEntity.ok().build();// RETURN TO AJAX PETITION OK RESPONSE
		} catch (DataIntegrityViolationException e) {// ERROR IF TRAIN IS ASIGNED TO A SCHEDULE AND TRY TO DELETE (DATABASE RESTRICTION VIOLATED)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("The train cannot be deleted if it has schedules assigned to it"); // RETURN TO AJAX PETITION ERROR RESPONSE AND MESSAGE
		}
	}

	@PostMapping("/admin/estacion/{id}")
	public ResponseEntity<Object> eliminarEstacion(@PathVariable Long id) { // RECEIVE THE ID
		try {
			estacionService.eliminarEstacion(id);
			return ResponseEntity.ok().build(); // si se elimina correctamente
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("The station cannot be deleted if it has schedules assigned to it"); // si hay una excepción
		}
	}

	@PostMapping("/admin/horario/{id}")
	public ResponseEntity<Object> eliminarHorario(@PathVariable Long id, RedirectAttributes redirectAttributes,
			HttpServletRequest request, Model model) { // RECEIVE THE ID
		try {
			horarioService.eliminarHorario(id);
			return ResponseEntity.ok().build(); // si se elimina correctamente
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("The schedule cannot be deleted if it has tickets assigned to it"); // si hay una excepción
		}
	}

	@PostMapping("/admin/pasajero/{id}")
	public ResponseEntity<Object> eliminarPasajero(@PathVariable String id) { // RECEIVE THE ID
		try {
			pasajeroService.eliminarPasajero(id);
			return ResponseEntity.ok().build(); // si se elimina correctamente
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("The user cannot be deleted if it has tickets purchased"); // si hay una excepción
		}
	}

	@PostMapping("/cerrarSesionAdmin")
	public String logout(HttpSession session) throws ServletException {
		session.removeAttribute("usuario"); // REMOVE THE USER TO BE ABLE TO CLOSE SESSION
		return "redirect:/";
	}
}