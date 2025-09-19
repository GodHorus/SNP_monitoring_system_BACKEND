// LabReportRepository.java
package com.example.master.repository;

import com.example.master.model.LabReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LabReportRepository extends JpaRepository<LabReport, Long> {
    List<LabReport> findByDemandId(Long demandId);
}
