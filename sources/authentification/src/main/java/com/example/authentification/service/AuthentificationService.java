package com.example.authentification.service;

import java.util.List;

import com.example.authentification.entities.Fonction;
import com.example.authentification.entities.Utilisateur;

public interface AuthentificationService {
	
	Utilisateur addUtilisateur (Utilisateur utilisateur);
	
	Fonction addFonction (Fonction fonction);
	
	void addFonctionToUtilisateur (String username, String role);
	
	Utilisateur findByUsername(String username);
	Fonction findByRole(String fonction);
	
	List<Utilisateur> findAllUtilisateurs();
}
