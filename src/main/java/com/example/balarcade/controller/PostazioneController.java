package com.example.balarcade.controller;

import com.example.balarcade.dto.PostazioneDTO;
import com.example.balarcade.dto.UtenteDTO;
import com.example.balarcade.service.provider.ServiceProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/postazione")
public class PostazioneController {
    private final ServiceProvider sp;

    @GetMapping
    public ResponseEntity<List<PostazioneDTO>> prendiPostazioni(@Valid @RequestBody UtenteDTO utenteDTO) {

        sp.utenteService.registrazioneUtente(utenteDTO.buildClienteModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
