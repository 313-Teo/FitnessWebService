package com.example.webservice.cards;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "codice", unique = true, nullable = false)
    private String codice;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "workout1")
    private String workout1;

    @Column(name = "workout2")
    private String workout2;

    @Column(name = "workout3")
    private String workout3;

    // Costruttori, getter e setter

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

    // Getter e Setter per cognome
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e Setter per workout1
    public String getWorkout1() {
        return workout1;
    }

    public void setWorkout1(String workout1) {
        this.workout1 = workout1;
    }

    // Getter e Setter per workout2
    public String getWorkout2() {
        return workout2;
    }

    public void setWorkout2(String workout2) {
        this.workout2 = workout2;
    }

    // Getter e Setter per workout3
    public String getWorkout3() {
        return workout3;
    }

    public void setWorkout3(String workout3) {
        this.workout3 = workout3;
    }
}


