package com.example.balarcade.controller;

import com.example.balarcade.dto.UtenteDTO;
import com.example.balarcade.service.provider.ServiceProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    private final ServiceProvider sp;

    @PostMapping("/registrazione")
    public ResponseEntity<Void> registrazioneUtente(@Valid @RequestBody UtenteDTO utenteDTO) {

        sp.utenteService.registrazioneUtente(utenteDTO.buildClienteModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
