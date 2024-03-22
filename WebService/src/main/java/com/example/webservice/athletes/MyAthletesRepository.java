package com.example.webservice.athletes;

import com.example.webservice.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyAthletesRepository extends JpaRepository<MyAthletes, Long> {

    @Query("SELECT m FROM MyAthletes m JOIN FETCH m.athlete WHERE m.trainer.codice = :codiceUtente")
    List<MyAthletes> findByCodice(@Param("codiceUtente") String codiceUtente);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MyAthletes m WHERE m.trainer = ?1 AND m.athlete = ?2")
    boolean existsByTrainerAndAthlete(User trainer, User athlete);
}

