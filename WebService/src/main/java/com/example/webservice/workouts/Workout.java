package com.example.webservice.workouts;

import javax.persistence.*;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @Column(name = "codice", unique = true, nullable = false)
    private String codice;

    @Column(name = "nome", unique = true)
    private String nome;

    // Getter e Setter

    // Getter e Setter per codice
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    // Getter e Setter per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

