package com.example.master.repository;

import com.example.master.model.Fci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FciRepository extends JpaRepository<Fci, Long> {
}
