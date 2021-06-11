package net.maihuire.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.maihuire.entity.Persona;
import net.maihuire.service.PersonaService;

@Controller

public class PersonaController {

	@Autowired
	// private PersonaRepository personaRepo; // metodo obcional
	private PersonaService personaService;

	@GetMapping("/")
	public String inicio(Model model, @AuthenticationPrincipal User user, @RequestParam(defaultValue = "0") int page) {

		model.addAttribute("personas", personaService.findAll(PageRequest.of(page, 1)));
		
		model.addAttribute("currentPage", page);
		var personas = personaService.listarPersonas();

		double saldoTotal = 0D;
		for (var p : personas) {
			saldoTotal += p.getSaldo();
		}
	
		// agrega los datos al target de tarjetas de colores
		model.addAttribute("saldoTotal", saldoTotal);
		model.addAttribute("totalClientes", personas.size());

		return "index";
	}
	

	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "modificar";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errores) {

		if (errores.hasErrors()) {
			return "modificar";
		}

		personaService.guardar(persona);

		return "redirect:/";
	}

	@GetMapping("/editar/{idpersona}")
	public String editar(Persona persona, Model model) {
		persona = personaService.encontrarPersona(persona);
		model.addAttribute("persona", persona);
		return "modificar";
	}

	@GetMapping("/eliminar/{idpersona}")
	public String eliminar(Persona persona) {
		personaService.eliminar(persona);
		return "redirect:/";

	}

}
