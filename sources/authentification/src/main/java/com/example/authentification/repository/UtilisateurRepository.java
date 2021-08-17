package com.example.authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authentification.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	Utilisateur findByUsername(String username);
}
