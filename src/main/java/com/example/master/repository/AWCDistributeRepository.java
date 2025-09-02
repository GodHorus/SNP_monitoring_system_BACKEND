package com.example.master.repository;

import com.example.master.model.AWCDistribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AWCDistributeRepository extends JpaRepository<AWCDistribute, Long> {
}
