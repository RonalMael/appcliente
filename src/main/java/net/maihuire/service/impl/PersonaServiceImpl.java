package net.maihuire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.maihuire.entity.Persona;
import net.maihuire.repository.PersonaRepository;
import net.maihuire.service.PersonaService;

@Service

public class PersonaServiceImpl implements PersonaService {

	
	@Autowired
	private  PersonaRepository personRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		
	  return personRepo.findAll();
		

	}

	@Override
	@Transactional
	public void guardar(Persona persona) {
		personRepo.save(persona);
		
	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
	personRepo.delete(persona);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) {
		
		return personRepo.findById(persona.getIdpersona()).orElse(null);
		
	}

	@Override
	public Page<Persona> findAll(Pageable pageable) {
		return personRepo.findAll(pageable);
	}

}
