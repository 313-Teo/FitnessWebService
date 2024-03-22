package com.example.webservice.athletes;

import com.example.webservice.users.User;
import javax.persistence.*;

@Entity
@Table(name = "myathletes")
public class MyAthletes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codice_trainer")
    private User trainer;

    @ManyToOne
    @JoinColumn(name = "codice_atleta")
    private User athlete;

    //Getter e Setter

    //Getter e Setter per 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter e Setter per 'trainer'
    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    //Getter e Setter per 'athlete'
    public User getAthlete() {
        return athlete;
    }

    public void setAthlete(User athlete) {
        this.athlete = athlete;
    }
}
