package com.proyecto.core.repository;

import com.proyecto.core.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

    @Query(value = "SELECT * FROM db_diseño.product_price WHERE product_id = ?1", nativeQuery = true)
    List<ProductPrice> findPricesByProductId(Integer productId);

    @Transactional
    void deleteProductPricesByProductId(Integer id);

}
