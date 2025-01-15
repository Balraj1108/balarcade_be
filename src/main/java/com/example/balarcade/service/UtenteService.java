package com.example.balarcade.service;

import com.example.balarcade.enumeration.RuoloUtente;
import com.example.balarcade.exception.BalarcadeException;
import com.example.balarcade.model.Utente;
import com.example.balarcade.repository.UtenteRepository;
import com.example.balarcade.service.provider.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Service
@RequiredArgsConstructor
public class UtenteService {

    private ServiceProvider sp;

    private final UtenteRepository repository;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void registrazioneUtente(Utente utente) {
        if (repository.existsByEmail((utente.getEmail()))){
            throw new BalarcadeException("L'email " + utente.getEmail() + " è già registrata.", HttpStatus.CONFLICT);
        }
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setRuolo(RuoloUtente.CLIENTE);
        repository.save(utente);
    }

    public Utente cercaPerId(Long id) {
        return repository.findById(id).get();
    }

}
