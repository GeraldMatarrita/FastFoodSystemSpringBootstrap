package com.proyecto.core.repository;
import com.proyecto.core.model.FoodLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodLineRepository extends JpaRepository<FoodLine, Integer> {

    FoodLine findFirstById(Integer id);
}
