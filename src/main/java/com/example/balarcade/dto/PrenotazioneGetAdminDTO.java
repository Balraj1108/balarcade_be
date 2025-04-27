package com.example.balarcade.dto;

import com.example.balarcade.enumeration.TipoPostazione;
import com.example.balarcade.model.Prenotazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PrenotazioneGetAdminDTO {

    private Long id;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private Long idPostazione;
    private TipoPostazione tipoPostazione;
    private String nomePostazione;
    private String nome;
    private String cognome;
    private String email;
    private Integer costoTotale;

    public static PrenotazioneGetAdminDTO fromEntity(Prenotazione prenotazione) {
        return PrenotazioneGetAdminDTO.builder()
                .id(prenotazione.getId())
                .dataInizio(prenotazione.getDataInizio())
                .dataFine(prenotazione.getDataFine())
                .idPostazione(prenotazione.getPostazione().getId())
                .tipoPostazione(prenotazione.getPostazione().getTipo())
                .nomePostazione(prenotazione.getPostazione().getNome())
                .nome(prenotazione.getUtente().getNome())
                .cognome(prenotazione.getUtente().getCognome())
                .email(prenotazione.getUtente().getEmail())
                .costoTotale(prenotazione.getCostoTotale())
                .build();
    }

    public static List<PrenotazioneGetAdminDTO> fromEntity(List<Prenotazione> prenotazione) {
        return prenotazione.stream().map(PrenotazioneGetAdminDTO::fromEntity).collect(Collectors.toList());
    }

}
