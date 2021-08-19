package com.example.authentification.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authentification.entities.Utilisateur;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthentificationService authentificationService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur utilisateur = authentificationService.findByUsername(username);
		
		List<GrantedAuthority> authorities = utilisateur.getFonctions()
				                                  .stream()
				                                  .map(fonction -> new SimpleGrantedAuthority(fonction.getRole()))						                                  
				                                  .collect(Collectors.toList());
		
		
		return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
	}
}
