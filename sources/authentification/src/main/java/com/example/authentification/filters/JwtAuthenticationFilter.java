package com.example.authentification.filters;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("==> attemptAuthentication");
		// Ici nous considérons que les informations ne sont pas envoyé dans un flux JSON (pour le POC).
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
				
		// C'est cette méthode qui fait appel à la méthode loadUserByUsername qui va vérifier le login et mot de passe en base.
		// En cas de succès, la méthode ci dessous successfulAuthentication est appelées
		return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("==> successfulAuthentication");
		
		User user = (User)authResult.getPrincipal();
		
		// Nous allons générer le token
		Algorithm algorithmHmac256 = Algorithm.HMAC256("MA_CLE_SECRETE");
		
		// Génération du JWT
		String jwt = JWT.create()
				        .withSubject(user.getUsername())
				        .withExpiresAt(new Date(System.currentTimeMillis()+5 * 60 *1000)) // 5 minutes
				        .withIssuer(request.getRequestURL().toString()) // Nom de l'application qui a générée le token
				        .withClaim("roles", 
				                	user.getAuthorities().stream()
				                		                 .map(authorities -> authorities.getAuthority())
				                		                 .collect(Collectors.toList()))
				                   						 .sign(algorithmHmac256);
				                   
		response.setHeader("Authorization", jwt);
		
	}
}
