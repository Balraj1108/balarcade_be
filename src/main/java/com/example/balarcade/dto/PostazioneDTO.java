package com.example.balarcade.dto;

import com.example.balarcade.enumeration.TipoPostazione;
import com.example.balarcade.model.Postazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

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

    public static PostazioneDTO fromEntity(Postazione postazione) {
        return PostazioneDTO.builder()
                .id(postazione.getId())
                .tipo(postazione.getTipo())
                .nome(postazione.getNome())
                .disponibile(postazione.getDisponibile())
                .descrizione(postazione.getDescrizione())
                .costoOra(postazione.getCostoOra())
                .build();
    }

    public static List<PostazioneDTO> fromEntity(List<Postazione> postazioni) {
        return postazioni.stream().map(PostazioneDTO::fromEntity).collect(Collectors.toList());
    }


}
