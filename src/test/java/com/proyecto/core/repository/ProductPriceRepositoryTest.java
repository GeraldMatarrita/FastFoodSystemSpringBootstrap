package com.proyecto.core.repository;

import com.proyecto.core.ProyectoApplication;
import com.proyecto.core.model.PriceType;
import com.proyecto.core.model.Product;
import com.proyecto.core.model.ProductPrice;
import com.proyecto.core.model.ProductPriceKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.persistence.EntityManager;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductPriceRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Test
    void findPrices() {
        ProductPrice productPrice = new ProductPrice(new ProductPriceKey(1, 2),
                new Product(1, "product", "products description", "product content", "imagen.jpg"),
                new PriceType(2, "priceType"),
                100
        );
        List<ProductPrice> pricesTested = new ArrayList<>();
        pricesTested.add(productPrice);

        assertEquals(pricesTested, productPriceRepository.findPricesByProductId(1));
    }

    @Test
    void deleteProductPricesByProductId() {
    }
}