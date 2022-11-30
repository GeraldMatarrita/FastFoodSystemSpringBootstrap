package com.proyecto.core.repository;

import com.proyecto.core.model.payment.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {

    PaymentType findFirstById(Integer id);
}
