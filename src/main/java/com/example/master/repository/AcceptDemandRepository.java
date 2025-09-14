package com.example.master.repository;

import com.example.master.model.AcceptDemand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcceptDemandRepository extends JpaRepository<AcceptDemand, Long> {
    Optional<AcceptDemand> findByDemandId(Long demandId);
}
