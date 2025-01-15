package com.example.balarcade.repository;

import com.example.balarcade.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findAllByUtenteId(Long utenteId);

}
