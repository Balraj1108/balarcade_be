package com.example.balarcade.controller;

import com.example.balarcade.dto.PostazioneDTO;
import com.example.balarcade.model.Postazione;
import com.example.balarcade.service.provider.ServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/postazione")
public class PostazioneController {
    private final ServiceProvider sp;

    @GetMapping
    public ResponseEntity<List<PostazioneDTO>> prendiPostazioni() {
        List<Postazione> postazioni = sp.postazioneService.prendiPostazioni();
        return ResponseEntity.ok(PostazioneDTO.fromEntity(postazioni));
    }
}
