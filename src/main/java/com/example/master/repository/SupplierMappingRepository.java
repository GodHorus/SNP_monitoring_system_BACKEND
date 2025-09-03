package com.example.master.repository;

import com.example.master.model.SupplierMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMappingRepository extends JpaRepository<SupplierMapping, Long> {
    List<SupplierMapping> findByDemandId(Long demandId);
}
