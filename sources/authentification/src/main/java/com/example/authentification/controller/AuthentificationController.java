package com.example.authentification.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentification.entities.Fonction;
import com.example.authentification.entities.Utilisateur;
import com.example.authentification.service.AuthentificationService;

@RestController
public class AuthentificationController {
	
	private AuthentificationService authentificationService;

	public AuthentificationController(AuthentificationService authentificationService) {		
		this.authentificationService = authentificationService;
	}	
	
	@GetMapping(path = "utilisateurs")
	public List<Utilisateur> findAllUtilisateurs() {
		return authentificationService.findAllUtilisateurs();
	}

	@PostMapping(path = "utilisateurs")
	public Utilisateur addUtilisateur (@RequestBody Utilisateur utilisateur) {
		return authentificationService.addUtilisateur(utilisateur);
	}
	
	@PostMapping(path = "fonctions")
	public Fonction addFonction (@RequestBody Fonction fonction) {
		return authentificationService.addFonction(fonction);
	}
		
	
	@GetMapping(path = "utilisateurs/{username}")
	public Utilisateur findByUsername(@PathVariable String username) {
		return authentificationService.findByUsername(username);
	}
	
	@GetMapping(path = "fonctions/{fonction}")
	public Fonction findByRole(String fonction) {
		return authentificationService.findByRole(fonction);
	}
	
}
