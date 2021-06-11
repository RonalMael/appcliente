package net.maihuire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.maihuire.entity.Persona;


public interface PersonaRepository extends JpaRepository<Persona, Long> {

	
}
