package com.example.balarcade.config;

import com.example.balarcade.enumeration.RuoloUtente;
import com.example.balarcade.enumeration.TipoPostazione;
import com.example.balarcade.model.Postazione;
import com.example.balarcade.model.Utente;
import com.example.balarcade.repository.PostazioneRepository;
import com.example.balarcade.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostazioneRepository postazioneRepository;

    public DataInitializer(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder, PostazioneRepository postazioneRepository) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.postazioneRepository = postazioneRepository;
    }
    //Inserisci dati db
    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Crea un nuovo utente admin
            if (utenteRepository.findByEmail("admin@email.com").isEmpty()) {
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

            // Inizializzazione postazioni
            if (postazioneRepository.count() == 0) {
                Postazione pc1 = Postazione.builder()
                        .tipo(TipoPostazione.PC)
                        .nome("Postazione PC 1")
                        .disponibile(true)
                        .descrizione("PC ad alte prestazioni per gaming.")
                        .costoOra(5)
                        .build();

                Postazione console1 = Postazione.builder()
                        .tipo(TipoPostazione.CONSOLE)
                        .nome("Postazione Console 1")
                        .disponibile(true)
                        .descrizione("Console di ultima generazione con giochi recenti.")
                        .costoOra(4)
                        .build();

                Postazione simulatore1 = Postazione.builder()
                        .tipo(TipoPostazione.SIMULATORE)
                        .nome("Simulatore Guida")
                        .disponibile(true)
                        .descrizione("Simulatore di guida con volante e pedali.")
                        .costoOra(10)
                        .build();

                postazioneRepository.save(pc1);
                postazioneRepository.save(console1);
                postazioneRepository.save(simulatore1);

                System.out.println("Postazioni inizializzate con successo!");
            } else {
                System.out.println("Postazioni già presenti nel database.");
            }
        };
    }
}

