package com.example.balarcade.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JWTFilter jwtFilter;
	private final CustomUserDetailsService customUserDetailsService;

	public SecurityConfig(JWTFilter jwtFilter, CustomUserDetailsService customUserDetailsService) {
		this.jwtFilter = jwtFilter;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	// Configurazione CORS
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(List.of("http://localhost:5200"));
		corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
		corsConfig.setExposedHeaders(List.of("Authorization"));
		corsConfig.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);  // Applica la configurazione CORS a tutte le endpoint
		return source;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.cors(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable) // Disabilita CSRF per le API stateless
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/api/auth/login", "/api/utente/registrazione", "/api/postazione").permitAll() // Permetti l'accesso pubblico al login e registrazione
						.requestMatchers("/admin").hasRole("ADMIN") // Accesso riservato agli amministratori
						.anyRequest().authenticated() // Tutte le altre richieste richiedono autenticazione
				)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Aggiungi il filtro JWT
				.build();
	}

}
