package com.example.balarcade.controller;

import com.example.balarcade.dto.PrenotazioneDTO;
import com.example.balarcade.service.provider.ServiceProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

    private final ServiceProvider sp;

    @PostMapping
    public ResponseEntity<Void> prenota(@Valid @RequestBody PrenotazioneDTO prenotazioneDTO) {
        sp.prenotazioneService.prenota(prenotazioneDTO);
        return ResponseEntity.ok().build();
    }
}
