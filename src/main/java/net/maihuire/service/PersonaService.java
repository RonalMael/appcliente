package net.maihuire.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.maihuire.entity.Persona;

public interface PersonaService {
	
	public  List<Persona> listarPersonas();
	
	public void guardar (Persona persona);
	
	public  void eliminar (Persona persona);
	
	public Persona encontrarPersona (Persona persona);
	
	//Paginacion
	public    Page<Persona> findAll(Pageable pageable);
	
	

}
