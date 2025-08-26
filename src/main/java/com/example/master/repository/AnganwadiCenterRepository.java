package com.example.master.repository;

import com.example.master.model.AnganwadiCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnganwadiCenterRepository extends JpaRepository<AnganwadiCenter, Long> {
    boolean existsById(Long id);
    List<AnganwadiCenter> findBySupervisorId(Long supervisorId);
}
