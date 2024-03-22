package com.example.webservice.exercises;

import com.example.webservice.workouts.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findByNome(String nome);

    @Query("SELECT e.nome FROM Exercise e")
    List<String> findAllNames();
}
