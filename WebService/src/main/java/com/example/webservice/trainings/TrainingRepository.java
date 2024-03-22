package com.example.webservice.trainings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("SELECT t FROM Training t JOIN FETCH t.exercise WHERE t.workout.codice = :codiceWorkout")
    List<Training> findByCodice(@Param("codiceWorkout") String codiceWorkout);
}
