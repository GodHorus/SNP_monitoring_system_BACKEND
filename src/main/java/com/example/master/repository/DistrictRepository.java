// repository/DistrictRepository.java
package com.example.master.repository;

import com.example.master.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByDistrictName(String districtName);
}
