package com.example.balarcade.repository;

import com.example.balarcade.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

    Optional<Utente> findByEmail(String email);

    boolean existsByEmail(String email);
}
