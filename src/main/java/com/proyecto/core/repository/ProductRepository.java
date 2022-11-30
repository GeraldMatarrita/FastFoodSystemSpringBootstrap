package com.proyecto.core.repository;

import com.proyecto.core.model.Product;
import com.proyecto.core.model.payment.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    Product findFirstById(Integer id);
}
