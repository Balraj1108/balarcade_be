package com.example.balarcade.service;

import com.example.balarcade.dto.PrenotazioneDTO;
import com.example.balarcade.enumeration.StatoPrenotazione;
import com.example.balarcade.model.Prenotazione;
import com.example.balarcade.repository.PrenotazioneRepository;
import com.example.balarcade.service.provider.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Setter
@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository repository;
    private ServiceProvider sp;

    public List<Prenotazione> prendiPrenotazioniUtente(Long utenteId) {
        return repository.findAllByUtenteId(utenteId);
    }

    @Transactional
    public void prenota(PrenotazioneDTO prenotazione) {
        repository.save(
                Prenotazione.builder()
                        .dataInizio(prenotazione.getDataInizio())
                        .dataFine(prenotazione.getDataFine())
                        .utente(sp.utenteService.cercaPerId(prenotazione.getUtenteId()))
                        .postazione(sp.postazioneService.cercaPerId(prenotazione.getPostazioneId()))
                        .costoTotale(prenotazione.getCostoTotale())
                        .stato(StatoPrenotazione.CONFERMATA)
                        .build()
        );
    }
}
