package com.example.master.repository;

import com.example.master.model.DispatchDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DispatchDetailRepository extends JpaRepository<DispatchDetail, Long> {
    List<DispatchDetail> findByDemandId(Long demandId);
    Optional<DispatchDetail> findTopByOrderByIdDesc();
}
