package com.example.balarcade.dto;

import com.example.balarcade.enumeration.TipoPostazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PostazioneDTO {
    private Long id;
    private TipoPostazione tipo;
    private String nome;
    private Boolean disponibile;
    private String descrizione;
    private Integer costoOra;
}
