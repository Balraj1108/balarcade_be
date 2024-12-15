package com.example.balarcade.service;

import com.example.balarcade.service.autenticazione.AutenticazioneService;
import com.example.balarcade.service.utente.UtenteService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ServiceProvider {

    public final AutenticazioneService autenticazioneService;
    public final UtenteService utenteService;

    @PostConstruct
    public void init() {
        autenticazioneService.setSp(this);
        utenteService.setSp(this);
    }
}