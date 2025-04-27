package com.example.balarcade.service;

import com.example.balarcade.dto.PrenotazionePostDTO;
import com.example.balarcade.enumeration.StatoPrenotazione;
import com.example.balarcade.exception.BalarcadeException;
import com.example.balarcade.model.Prenotazione;
import com.example.balarcade.repository.PrenotazioneRepository;
import com.example.balarcade.service.provider.ServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
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

    public List<Prenotazione> prendiTuttePrenotazioni() {
        return repository.findAll();
    }

    @Transactional
    public void eliminaPrenotazione(Long idPrenotazione) {
        Prenotazione prenotazione = repository.findById(idPrenotazione)
                .orElseThrow(() -> new BalarcadeException("Prenotazione non trovata", HttpStatus.NOT_FOUND));

        repository.delete(prenotazione);
    }

    @Transactional
    public void modificaPrenotazione(PrenotazionePostDTO prenotazionePostDTO) {
        if(prenotazionePostDTO.getPrenotazioneId() == null){
            throw new BalarcadeException("prenotazioneId non puo essere null.", HttpStatus.BAD_REQUEST);
        }
        // Recupera la prenotazione esistente
        Prenotazione prenotazioneEsistente = repository.findById(prenotazionePostDTO.getPrenotazioneId())
                .orElseThrow(() -> new BalarcadeException("Prenotazione non trovata.", HttpStatus.NOT_FOUND));

        // Valida i dati della prenotazione
        validaPrenotazione(prenotazionePostDTO);

        // Aggiorna i dati della prenotazione
        prenotazioneEsistente.setDataInizio(prenotazionePostDTO.getDataInizio());
        prenotazioneEsistente.setDataFine(prenotazionePostDTO.getDataFine());
        prenotazioneEsistente.setCostoTotale(prenotazionePostDTO.getCostoTotale());
        prenotazioneEsistente.setStato(StatoPrenotazione.MODIFICATA);
        prenotazioneEsistente.setPostazione(sp.postazioneService.cercaPerId(prenotazionePostDTO.getPostazioneId()));

        // Salva i cambiamenti
        repository.save(prenotazioneEsistente);
    }


    @Transactional
    public void prenota(PrenotazionePostDTO prenotazionePostDTO) {
        // Valida i dati della prenotazione
        validaPrenotazione(prenotazionePostDTO);

        // Salva la nuova prenotazione
        repository.save(
                Prenotazione.builder()
                        .dataInizio(prenotazionePostDTO.getDataInizio())
                        .dataFine(prenotazionePostDTO.getDataFine())
                        .utente(sp.utenteService.cercaPerId(prenotazionePostDTO.getUtenteId()))
                        .postazione(sp.postazioneService.cercaPerId(prenotazionePostDTO.getPostazioneId()))
                        .costoTotale(prenotazionePostDTO.getCostoTotale())
                        .stato(StatoPrenotazione.CONFERMATA)
                        .build()
        );
    }

    private void validaPrenotazione(PrenotazionePostDTO prenotazionePostDTO) {
        LocalDateTime oggi = LocalDateTime.now();

        // Controllo che la data di inizio non sia oggi o in una data passata
        if (!prenotazionePostDTO.getDataInizio().isAfter(oggi.toLocalDate().atStartOfDay())) {
            throw new BalarcadeException("Non è possibile prenotare per oggi o una data passata.", HttpStatus.BAD_REQUEST);
        }

        // Controllo che la data di fine non sia antecedente alla data di inizio
        if (prenotazionePostDTO.getDataFine().isBefore(prenotazionePostDTO.getDataInizio())) {
            throw new BalarcadeException("La data di fine non può essere precedente alla data di inizio.", HttpStatus.BAD_REQUEST);
        }

        // Controllo che l'intervallo tra dataFine e dataInizio non superi le 12 ore
        Duration durata = Duration.between(prenotazionePostDTO.getDataInizio(), prenotazionePostDTO.getDataFine());
        if (durata.toHours() > 12) {
            throw new BalarcadeException("La durata della prenotazione non può essere maggiore di 12 ore.", HttpStatus.BAD_REQUEST);
        }

        // Verifica se esiste una prenotazione sovrapposta
        boolean esisteSovrapposizione = repository.esistePrenotazioneSovrapposta(
                prenotazionePostDTO.getPostazioneId(),
                prenotazionePostDTO.getDataInizio(),
                prenotazionePostDTO.getDataFine()
        );

        if (esisteSovrapposizione) {
            throw new BalarcadeException("La postazione è già prenotata in questo intervallo di tempo.", HttpStatus.BAD_REQUEST);
        }
    }
}
