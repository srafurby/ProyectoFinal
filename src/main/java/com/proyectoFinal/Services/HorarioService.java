package com.proyectoFinal.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Entidades.Horario;
import com.proyectoFinal.Interfaces.HorarioRepository;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;

	public void eliminarHorario(Long id) {
		horarioRepository.deleteById(id);
	}
	
	public List<Horario> buscarHorarioIdEstacion(Long id_salida,Long id_llegada){
		List<Horario> horarios = horarioRepository.findAll();
		List<Horario> res = new ArrayList<Horario>();
		for(Horario horario : horarios) {
			if(horario.getEstacion_salida().getID_estacion().equals(id_salida) && horario.getEstacion_llegada().getID_estacion().equals(id_llegada)) {
				res.add(horario);
			}
		}
		
		return res;
	}
}