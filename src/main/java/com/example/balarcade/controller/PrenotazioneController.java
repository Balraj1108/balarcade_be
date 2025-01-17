package com.example.balarcade.controller;

import com.example.balarcade.dto.PrenotazioneGetDTO;
import com.example.balarcade.dto.PrenotazionePostDTO;
import com.example.balarcade.model.Prenotazione;
import com.example.balarcade.service.provider.ServiceProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {

    private final ServiceProvider sp;

    @PostMapping
    public ResponseEntity<Void> prenota(@Valid @RequestBody PrenotazionePostDTO prenotazionePostDTO) {
        sp.prenotazioneService.prenota(prenotazionePostDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<PrenotazioneGetDTO>> prendiPrenotazioni(@RequestParam("id") Long id) {
        List<Prenotazione> prenotazioni = sp.prenotazioneService.prendiPrenotazioniUtente(id);
        return ResponseEntity.ok(PrenotazioneGetDTO.fromEntity(prenotazioni));
    }

}
