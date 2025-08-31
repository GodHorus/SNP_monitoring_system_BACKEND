package com.example.master.repository;

import com.example.master.model.BatchDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchDetailRepository extends JpaRepository<BatchDetail, Long> {

    // This works because BatchDetail.ingredient.id is the field path
//    BatchDetail findByIngredientId(Long ingredientId);
    Optional<BatchDetail> findByIngredientId(Long ingredientId);
}
