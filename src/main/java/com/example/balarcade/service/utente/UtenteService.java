package com.example.balarcade.service.utente;

import com.example.balarcade.enumeration.RuoloUtente;
import com.example.balarcade.model.Utente;
import com.example.balarcade.repository.utente.UtenteRepository;
import com.example.balarcade.service.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Service
@RequiredArgsConstructor
public class UtenteService {

    private ServiceProvider sp;

    @Autowired
    private UtenteRepository repository;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void registrazioneUtente(Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utente.setRuolo(RuoloUtente.CLIENTE);
        repository.save(utente);
    }

}
