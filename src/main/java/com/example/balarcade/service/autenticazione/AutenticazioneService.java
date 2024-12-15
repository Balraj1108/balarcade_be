package com.example.balarcade.service.autenticazione;

import com.example.balarcade.service.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Service
@RequiredArgsConstructor
public class AutenticazioneService {

    private ServiceProvider sp;

}
