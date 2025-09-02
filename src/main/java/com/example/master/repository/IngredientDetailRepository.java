package com.example.master.repository;

import com.example.master.model.IngredientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientDetailRepository extends JpaRepository<IngredientDetail, Long> {

    @Query(value = "SELECT batch_no FROM ingredient_details ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastBatchNo();

    @Query("SELECT i FROM IngredientDetail i WHERE i.demand.id = :demandId")
    List<IngredientDetail> findByDemandId(@Param("demandId") Long demandId);

}