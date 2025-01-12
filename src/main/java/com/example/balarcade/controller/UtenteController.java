package com.example.balarcade.controller;

import com.example.balarcade.dto.UtenteDTO;
import com.example.balarcade.service.ServiceProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {


    @Autowired
    private ServiceProvider sp;



    @PostMapping("/registrazione")
    public ResponseEntity<Void> registrazioneUtente(@Valid @RequestBody UtenteDTO utenteDTO) {

        sp.utenteService.registrazioneUtente(utenteDTO.buildClienteModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
