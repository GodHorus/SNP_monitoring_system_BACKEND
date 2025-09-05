package com.example.master.repository;

import com.example.master.model.DemandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandProductRepository extends JpaRepository<DemandProduct, Long> {}