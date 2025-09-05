package com.example.master.repository;

import com.example.master.model.DemandCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandCategoryRepository extends JpaRepository<DemandCategory, Long> {
    // Custom queries can be added here if needed
}
