package com.example.balarcade.service.provider;

import com.example.balarcade.service.AutenticazioneService;
import com.example.balarcade.service.PostazioneService;
import com.example.balarcade.service.PrenotazioneService;
import com.example.balarcade.service.UtenteService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ServiceProvider {

    public final AutenticazioneService autenticazioneService;
    public final UtenteService utenteService;
    public final PostazioneService postazioneService;
    public final PrenotazioneService prenotazioneService;

    @PostConstruct
    public void init() {
        autenticazioneService.setSp(this);
        utenteService.setSp(this);
        postazioneService.setSp(this);
        prenotazioneService.setSp(this);
    }
}
