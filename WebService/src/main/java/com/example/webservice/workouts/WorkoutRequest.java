package com.example.webservice.workouts;

import java.util.List;

public class WorkoutRequest {

    private String codice;
    private String nome;
    private List<String> esercizi;

    // Getter e Setter

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getEsercizi() {
        return esercizi;
    }

    public void setEsercizi(List<String> esercizi) {
        this.esercizi = esercizi;
    }
}
