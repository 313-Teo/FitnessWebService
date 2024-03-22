package com.example.webservice.exercises;
import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @Column(name = "codice", unique = true, nullable = false)
    private String codice;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "tempo")
    private String tempo;

    @Column(name = "focus")
    private String focus;

    //Getter e Setter

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

    // Getter e Setter per tempo
    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    // Getter e Setter per focus
    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }
}

