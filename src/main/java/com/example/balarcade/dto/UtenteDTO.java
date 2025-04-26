package com.example.balarcade.dto;

import com.example.balarcade.model.Utente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteDTO {
    protected String email;
    protected String password;
    protected String nome;
    protected String cognome;
    protected String numero;
    protected Long id;
    protected String ruolo;

    public UtenteDTO(String nome, String cognome, String email, Long id, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.id = id;
        this.ruolo = ruolo;
    }

    public Utente buildClienteModel() {
        return new Utente(this.nome, this.cognome, this.numero, this.email, this.password);
    }

    public static UtenteDTO fromModel(Utente utente) {
        return new UtenteDTO(
                utente.getNome(),
                utente.getCognome(),
                utente.getEmail(),
                utente.getId(),
                utente.getRuolo().toString()
        );
    }

}
