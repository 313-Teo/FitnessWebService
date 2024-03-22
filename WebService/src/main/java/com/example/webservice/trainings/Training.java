package com.example.webservice.trainings;

import com.example.webservice.workouts.Workout;
import com.example.webservice.exercises.Exercise;
import javax.persistence.*;

@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codice_workout")
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "codice_esercizio")
    private Exercise exercise;

    //Getter e Setter

    // Getter e Setter per 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per 'workout'
    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    // Getter e Setter per 'exercise'
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

}
