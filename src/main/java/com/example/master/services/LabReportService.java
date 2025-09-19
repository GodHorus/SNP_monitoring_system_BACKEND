// LabReportService.java
package com.example.master.services;

import com.example.master.model.LabReport;
import java.util.List;

public interface LabReportService {
    LabReport createLabReport(LabReport labReport);
    List<LabReport> findByDemandId(Long demandId);
}
