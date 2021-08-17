package com.example.authentification.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// https://www.youtube.com/watch?v=p_PhNjBukg8&ab_channel=Sourcesdel%27InformaticienavecPr.MohamedYOUSSFI
	// 34:11
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
	
	@Override
	public void configure(HttpSecurity http)  throws Exception  {
		// Désactivation du CSRF
		http.csrf().disable();
		
		// Nous désactivons la protection contre les frames pour afficher "h2-console" 
		http.headers().frameOptions().disable();
		
		// Indique que toutes les requêtes ne nécessite pas une autorisations
		http.authorizeRequests().anyRequest().permitAll();
	}
}
