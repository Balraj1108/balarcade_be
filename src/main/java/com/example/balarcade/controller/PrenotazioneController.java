package com.example.balarcade.controller;

import com.example.balarcade.dto.PrenotazioneGetDTO;
import com.example.balarcade.dto.PrenotazionePostDTO;
import com.example.balarcade.model.Prenotazione;
import com.example.balarcade.service.provider.ServiceProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @DeleteMapping
    public ResponseEntity<Void> eliminaPrenotazione(@RequestParam("idPrenotazione") Long idPrenotazione) {
        sp.prenotazioneService.eliminaPrenotazione(idPrenotazione);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> modificaPrenotazione(@Valid @RequestBody PrenotazionePostDTO prenotazionePostDTO) {
        sp.prenotazioneService.modificaPrenotazione(prenotazionePostDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tutte")
    public ResponseEntity<List<PrenotazioneGetDTO>> prendiTuttePrenotazioni(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<Prenotazione> prenotazioni = sp.prenotazioneService.prendiTuttePrenotazioni();
        return ResponseEntity.ok(PrenotazioneGetDTO.fromEntity(prenotazioni));
    }

}
