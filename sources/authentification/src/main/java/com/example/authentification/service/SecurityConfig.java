package com.example.authentification.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.authentification.entities.Utilisateur;
import com.example.authentification.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthentificationService authentificationService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		// L'authentification est à implémenter dans une classe qui implémente l'interface UserDetailsService
		authenticationManagerBuilder.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				Utilisateur utilisateur = authentificationService.findByUsername(username);
				
				List<GrantedAuthority> authorities = utilisateur.getFonctions()
						                                  .stream()
						                                  .map(fonction -> new SimpleGrantedAuthority(fonction.getRole()))						                                  
						                                  .collect(Collectors.toList());
				
				
				return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
			}
		});
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity)  throws Exception  {
		// Désactivation du CSRF (authentification statefull par session et cookies)
		httpSecurity.csrf().disable();		
		
		// Nous utilisons une authentification par toket et pas par session (stateless)
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Nous désactivons la protection contre les frames pour afficher "h2-console" 
		httpSecurity.headers().frameOptions().disable();
		
		// Pour afficher la mire de saisie de login et mot de passe Spring (plus besoin car authentification STATELESS)
		//httpSecurity.formLogin();
		
		// Accès à la console "h2-console" sans authentification
		httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		
		// Indique que toutes les requêtes nécessite une autorisations
		httpSecurity.authorizeRequests().anyRequest().authenticated();
		
		// Ajout du filtre d'authentification (réutilisation du bean du contexte)
		httpSecurity.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
	}
	
	/**
	 * Ajout dans le contexte de l'objet AuthenticationManager;
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
