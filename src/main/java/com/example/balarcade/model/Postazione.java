package com.example.balarcade.model;

import com.example.balarcade.enumeration.TipoPostazione;
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
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TipoPostazione tipo;
    private String nome;
    private Boolean disponibile;
    private String descrizione;
    private Integer costoOra;

    @OneToMany(mappedBy = "postazione", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Prenotazione> prenotazioni = new HashSet<>();

}
