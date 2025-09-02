package com.example.master.repository;

import com.example.master.model.AWCAcceptDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AWCAcceptDemandRepository extends JpaRepository<AWCAcceptDemand, Long> {
}
