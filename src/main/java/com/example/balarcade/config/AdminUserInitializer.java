package com.example.balarcade.config;

import com.example.balarcade.enumeration.RuoloUtente;
import com.example.balarcade.model.Utente;
import com.example.balarcade.repository.utente.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserInitializer {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initializeAdminUser() {
        return args -> {
            // Controlla se l'utente admin esiste già
            if (utenteRepository.findByEmail("admin@email.com").isEmpty()) {
                // Crea un nuovo utente admin
                Utente admin = new Utente();
                admin.setNome("admin_nome");
                admin.setCognome("admin_cognome");
                admin.setNumero("0123456789");
                admin.setPassword(passwordEncoder.encode("admin123")); // Password hashata
                admin.setEmail("admin@email.com");
                admin.setRuolo(RuoloUtente.ADMIN); // Associa il ruolo admin

                // Salva nel database
                utenteRepository.save(admin);
                System.out.println("Utente admin creato con successo!");
            } else {
                System.out.println("Utente admin già presente nel database.");
            }
        };
    }
}

