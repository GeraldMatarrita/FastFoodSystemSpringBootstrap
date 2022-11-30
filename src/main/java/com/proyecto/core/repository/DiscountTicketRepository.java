package com.proyecto.core.repository;

import com.proyecto.core.model.payment.DiscountTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountTicketRepository extends JpaRepository<DiscountTicket, String> {

    DiscountTicket findFirstById(String id);
}
