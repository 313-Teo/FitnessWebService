package com.example.webservice.cards;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCodice(String codice);
}

