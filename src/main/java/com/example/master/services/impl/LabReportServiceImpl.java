// LabReportServiceImpl.java
package com.example.master.services.impl;

import com.example.master.model.LabReport;
import com.example.master.repository.LabReportRepository;
import com.example.master.services.LabReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabReportServiceImpl implements LabReportService {

    private final LabReportRepository repo;

    public LabReportServiceImpl(LabReportRepository repo){ this.repo = repo; }

    @Override
    public LabReport createLabReport(LabReport labReport) {
        return repo.save(labReport);
    }

    @Override
    public List<LabReport> findByDemandId(Long demandId) {
        return repo.findByDemandId(demandId);
    }
}
