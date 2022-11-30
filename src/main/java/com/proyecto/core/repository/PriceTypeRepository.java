package com.proyecto.core.repository;

import com.proyecto.core.model.PriceType;
import com.proyecto.core.model.payment.ProcessorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PriceTypeRepository extends JpaRepository<PriceType, Integer> {

    PriceType findFirstById(Integer id);
}
