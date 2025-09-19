// IngredientRepository.java
package com.example.master.repository;

import com.example.master.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByDemandId(Long demandId);
}
