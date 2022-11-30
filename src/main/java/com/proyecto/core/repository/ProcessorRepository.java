package com.proyecto.core.repository;

import com.proyecto.core.model.payment.ProcessorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProcessorRepository extends JpaRepository<ProcessorPayment, String> {

    ProcessorPayment findFirstById(String id);

    @Query(value = "SELECT * FROM db_diseño.payment_processors WHERE payment_type = 1 AND state = 1", nativeQuery = true)
    Optional<ProcessorPayment> theresActiveCardProcessor();
    @Query(value = "SELECT * FROM db_diseño.payment_processors WHERE payment_type = 3 ORDER BY payment_type ASC LIMIT 1", nativeQuery = true)
    Optional<ProcessorPayment> theresCashProcessor();
    @Query(value = "SELECT * FROM db_diseño.payment_processors WHERE payment_type = 2 AND state = 1", nativeQuery = true)
    Optional<ProcessorPayment> theresActiveCheckProcessor();

}
