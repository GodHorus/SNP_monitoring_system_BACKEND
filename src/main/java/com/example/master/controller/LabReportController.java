// LabReportController.java
package com.example.master.controller;

import com.example.master.Dto.LabReportDTO;
import com.example.master.model.LabReport;
import com.example.master.services.LabReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/lab-reports")
public class LabReportController {

    private final LabReportService service;

    public LabReportController(LabReportService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<LabReport> create(@RequestBody LabReportDTO dto){
        LabReport lr = new LabReport();
        lr.setDemandId(dto.demandId);
        lr.setLabName(dto.getLabName());
        lr.setManufacturingDate(dto.getManufacturingDate());
        lr.setExpiryDate(dto.getExpiryDate());
        lr.setTestDate(dto.getTestDate());
        lr.setStatus(dto.getStatus());
        lr.setRemarks(dto.getRemarks());
        lr.setFilePath(dto.getFilePath());

        LabReport saved = service.createLabReport(lr);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/by-demand/{demandId}")
    public ResponseEntity<List<LabReport>> listByDemand(@PathVariable Long demandId){
        return ResponseEntity.ok(service.findByDemandId(demandId));
    }
}
