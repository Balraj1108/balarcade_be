package com.example.balarcade.security;

import com.example.balarcade.model.Utente;
import com.example.balarcade.repository.utente.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente user = utenteRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				true, true, true, true, getAuthorities(user));

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Utente user) {
		return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRuolo().name()));
	}


}
