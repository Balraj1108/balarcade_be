package com.example.balarcade.dto;

import com.example.balarcade.model.Utente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
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

    public Utente buildClienteModel() {
        return new Utente(this.nome, this.cognome, this.numero, this.email, this.password);
    }

}
