package com.example.master.controller;

import com.example.master.Dto.DemandCdpoDetailRequestDTO;
import com.example.master.Dto.DemandCdpoDetailResponseDTO;
import com.example.master.services.DemandCdpoDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demand-cdpo-details")
public class DemandCdpoDetailController {

    private final DemandCdpoDetailService detailService;

    public DemandCdpoDetailController(DemandCdpoDetailService detailService) {
        this.detailService = detailService;
    }

    @PostMapping
    public ResponseEntity<DemandCdpoDetailResponseDTO> createDetail(@RequestBody DemandCdpoDetailRequestDTO dto) {
        return ResponseEntity.ok(detailService.createDetail(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DemandCdpoDetailResponseDTO>> createMultiple(@RequestBody List<DemandCdpoDetailRequestDTO> dtos) {
        return ResponseEntity.ok(detailService.createMultipleDetails(dtos));
    }

    @GetMapping("/demand/{demandId}")
    public ResponseEntity<List<DemandCdpoDetailResponseDTO>> getByDemand(@PathVariable Long demandId) {
        return ResponseEntity.ok(detailService.getDetailsByDemand(demandId));
    }
}
