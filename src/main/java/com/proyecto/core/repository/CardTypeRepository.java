package com.proyecto.core.repository;

import com.proyecto.core.model.payment.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {

    CardType findFirstById(Integer id);
}
