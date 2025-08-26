package com.example.master.repository;

import com.example.master.model.Cdpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdpoRepository extends JpaRepository<Cdpo, Long> {
    List<Cdpo> findByDistrictId(Long districtId);
}
