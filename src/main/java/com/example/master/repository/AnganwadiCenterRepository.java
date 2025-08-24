package com.example.master.repository;

import com.example.master.model.AnganwadiCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnganwadiCenterRepository extends JpaRepository<AnganwadiCenter, Long> {
    boolean existsByCenterId(String centerId);
}
