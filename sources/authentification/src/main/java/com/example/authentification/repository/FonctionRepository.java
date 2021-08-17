package com.example.authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authentification.entities.Fonction;

public interface FonctionRepository extends JpaRepository<Fonction, Long> {

	Fonction findByRole(String fonction);
}
