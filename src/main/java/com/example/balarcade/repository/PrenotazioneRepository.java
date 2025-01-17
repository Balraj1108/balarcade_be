package com.example.balarcade.repository;

import com.example.balarcade.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findAllByUtenteId(Long utenteId);

    @Query("""
        SELECT COUNT(p) > 0
        FROM Prenotazione p
        WHERE p.postazione.id = :postazioneId
          AND p.dataFine > :dataInizio
          AND p.dataInizio < :dataFine
    """)
    boolean esistePrenotazioneSovrapposta(Long postazioneId, LocalDateTime dataInizio, LocalDateTime dataFine);

}
