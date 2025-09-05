package com.example.master.repository;

import com.example.master.model.ProductCommodityQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommodityQuantityRepository extends JpaRepository<ProductCommodityQuantity, Long> {
}
