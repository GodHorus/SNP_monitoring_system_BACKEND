package com.example.master.repository;

import com.example.master.model.DemandProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandProductQuantityRepository extends JpaRepository<DemandProductQuantity, Long> {
    List<DemandProductQuantity> findByDemandId(Long demandId);
}
