package com.example.master.repository;

import com.example.master.model.Benificiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Benificiary, Long> {
}
