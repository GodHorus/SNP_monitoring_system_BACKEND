package com.example.master.repository;

import com.example.master.model.BatchDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BatchDetailRepository extends JpaRepository<BatchDetail, Long> {

    // This works because BatchDetail.ingredient.id is the field path
//    BatchDetail findByIngredientId(Long ingredientId);
//    Optional<BatchDetail> findByIngredientId(Long ingredientId);
//    Optional<BatchDetail> findByLabReportIsNull();

    @Query("SELECT b FROM BatchDetail b JOIN b.ingredients i WHERE i.batchNo = :batchNo")
    Optional<BatchDetail> findByIngredients_BatchNo(@Param("batchNo") String batchNo);

    @Query("SELECT b FROM BatchDetail b JOIN b.ingredients i WHERE i.id = :ingredientId")
    Optional<BatchDetail> findByIngredientId(@Param("ingredientId") Long ingredientId);
    Optional<BatchDetail> findByIngredients_Id(Long ingredientId);
    List<BatchDetail> findByLabReportIsNull();

//    Optional<Object> findByIngredientId(Long ingredientId);
}
