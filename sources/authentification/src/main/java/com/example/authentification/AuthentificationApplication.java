package com.example.authentification;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.authentification.entities.Fonction;
import com.example.authentification.entities.Utilisateur;
import com.example.authentification.service.AuthentificationService;

@SpringBootApplication
public class AuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}

	@Bean
	CommandLineRunner start(AuthentificationService authentificationService) {
		return args -> {
			authentificationService.addFonction(new Fonction(null,"USER"));
			authentificationService.addFonction(new Fonction(null,"ADMIN"));
			authentificationService.addFonction(new Fonction(null,"MANAGER"));
			
			authentificationService.addUtilisateur(new Utilisateur(null, "user1", "1234", new ArrayList<>()));
			authentificationService.addUtilisateur(new Utilisateur(null, "user2", "1234", new ArrayList<>()));
			authentificationService.addUtilisateur(new Utilisateur(null, "admin", "1234", new ArrayList<>()));
			
			authentificationService.addFonctionToUtilisateur("user1", "USER");
			authentificationService.addFonctionToUtilisateur("user2", "USER");
			authentificationService.addFonctionToUtilisateur("admin", "ADMIN");
		};
	}
}
