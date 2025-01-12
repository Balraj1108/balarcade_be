package com.example.balarcade.config;

import com.example.balarcade.enumeration.RuoloUtente;
import com.example.balarcade.model.Utente;
import com.example.balarcade.repository.utente.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AdminUserInitializer {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Inserisci dati db
    @Bean
    public CommandLineRunner initializeAdminUser() {
        return args -> {
            if (utenteRepository.findByEmail("admin@email.com").isEmpty()) {
                // Crea un nuovo utente admin
                Utente admin = new Utente();
                admin.setNome("admin_nome");
                admin.setCognome("admin_cognome");
                admin.setNumero("0123456789");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@email.com");
                admin.setRuolo(RuoloUtente.ADMIN);

                utenteRepository.save(admin);
                System.out.println("Utente admin creato con successo!");
            } else {
                System.out.println("Utente admin già presente nel database.");
            }
        };
    }
}

