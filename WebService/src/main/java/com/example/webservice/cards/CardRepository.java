package com.example.webservice.cards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c JOIN FETCH c.workout WHERE c.user.codice = :codiceUtente")
    List<Card> findByCodice(@Param("codiceUtente") String codiceUtente);
}
