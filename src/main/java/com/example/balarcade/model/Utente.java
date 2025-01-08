package com.example.balarcade.model;

import com.example.balarcade.enumeration.RuoloUtente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    protected RuoloUtente ruolo;
    protected String email;
    protected String password;
    protected String nome;
    protected String cognome;
    protected String numero;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Utente(String nome, String cognome, String numero, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.numero = numero;
        this.email = email;
        this.password = password;
    }
}
