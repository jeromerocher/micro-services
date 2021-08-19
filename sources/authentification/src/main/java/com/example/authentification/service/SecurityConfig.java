package com.example.authentification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.authentification.filters.JwtAuthenticationFilter;
import com.example.authentification.filters.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		// L'authentification est à implémenter dans une classe qui implémente l'interface UserDetailsService
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
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
	
		
		//httpSecurity.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
        
		// Indique que toutes les requêtes nécessite une autorisations
		httpSecurity.authorizeRequests().anyRequest().authenticated();
		
		// Ajout du filtre d'authentification (réutilisation du bean du contexte)
		httpSecurity.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
		
		httpSecurity.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
