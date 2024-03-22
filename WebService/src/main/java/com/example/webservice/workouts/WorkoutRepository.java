package com.example.webservice.workouts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT w.nome FROM Workout w")
    List<String> findAllNames();

    Workout findByNome(String nome);
}
