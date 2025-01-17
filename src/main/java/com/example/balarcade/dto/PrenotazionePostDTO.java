package com.example.balarcade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PrenotazionePostDTO {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private Long utenteId;
    private Long postazioneId;
    private Long prenotazioneId;
    private Integer costoTotale;

}
