package com.example.authentification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.authentification.entities.Fonction;
import com.example.authentification.entities.Utilisateur;
import com.example.authentification.repository.FonctionRepository;
import com.example.authentification.repository.UtilisateurRepository;

@Service
@Transactional
public class AuthentificationServiceImpl implements AuthentificationService{
	
	private UtilisateurRepository utilisateurRepository;

	private FonctionRepository fonctionRepository;

	private PasswordEncoder passwordEncoder;
	
	public AuthentificationServiceImpl(	UtilisateurRepository utilisateurRepository,
										FonctionRepository fonctionRepository,
										PasswordEncoder passwordEncoder) {
		
		this.utilisateurRepository = utilisateurRepository;
		this.fonctionRepository = fonctionRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
			
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Fonction addFonction(Fonction fonction) {
		
		return fonctionRepository.save(fonction);
	}

	@Override
	public void addFonctionToUtilisateur(String username, String role) {
		Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
		Fonction fonction = fonctionRepository.findByRole(role);
		
		utilisateur.getFonctions().add(fonction);
		
	}

	@Override
	public Utilisateur findByUsername(String username) {		
		return utilisateurRepository.findByUsername(username);
	}

	@Override
	public Fonction findByRole(String fonction) {
		
		return fonctionRepository.findByRole(fonction);
	}

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		
		return utilisateurRepository.findAll();
	}
	
}
