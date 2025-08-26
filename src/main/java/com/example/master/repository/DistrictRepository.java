package com.example.master.repository;

import com.example.master.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
//    District findByDistrictName(String districtName);
}
