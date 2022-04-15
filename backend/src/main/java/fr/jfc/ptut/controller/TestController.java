package fr.jfc.ptut.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
	
    @GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/etudiant")
	@PreAuthorize("hasRole('ROLE_ETUDIANT')")
	public String etudAccess() {
		return "ROLE_ETUDIANT Content.";
	}
	
    @GetMapping("/tuteur")
	@PreAuthorize("hasRole('ROLE_TUTEUR')")
	public String tuteurAccess() {
		return "ROLE_TUTEUR Board.";
	}
	
    @GetMapping("/responsableDesStages")
	@PreAuthorize("hasRole('ROLE_RESPONSABLESTAGES')")
	public String respAccess() {
		return "Responsable Board.";
	}
}