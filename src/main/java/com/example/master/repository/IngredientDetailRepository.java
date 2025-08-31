package com.example.master.repository;

import com.example.master.model.IngredientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDetailRepository extends JpaRepository<IngredientDetail, Long> {

    @Query(value = "SELECT batch_no FROM ingredient_details ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastBatchNo();
}