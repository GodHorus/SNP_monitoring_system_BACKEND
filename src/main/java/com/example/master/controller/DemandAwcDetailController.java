package com.example.master.controller;

import com.example.master.Dto.DemandAwcDetailDTO;
import com.example.master.model.DemandAwcDetail;
import com.example.master.services.DemandAwcDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand-awc-details")
public class DemandAwcDetailController {

    private final DemandAwcDetailService demandAwcDetailService;

    public DemandAwcDetailController(DemandAwcDetailService demandAwcDetailService) {
        this.demandAwcDetailService = demandAwcDetailService;
    }

    @GetMapping
    public ResponseEntity<List<DemandAwcDetail>> getAll() {
        return ResponseEntity.ok(demandAwcDetailService.getAll());
    }

    // ✅ Create AWC Demand Details for a Demand
    @PostMapping("/{demandId}")
    public ResponseEntity<DemandAwcDetail> createDetail(
            @PathVariable Long demandId,
            @RequestBody DemandAwcDetailDTO dto) {
        DemandAwcDetail detail = demandAwcDetailService.createDemandAwcDetail(demandId, dto);
        return ResponseEntity.ok(detail);
    }

    // ✅ Get all AWC details for a Demand
    @GetMapping("/{demandId}")
    public ResponseEntity<List<DemandAwcDetail>> getDetailsByDemand(@PathVariable Long demandId) {
        List<DemandAwcDetail> details = demandAwcDetailService.getDemandAwcDetailsByDemand(demandId);
        return ResponseEntity.ok(details);
    }
}
