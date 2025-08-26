package com.example.master.controller;

import com.example.master.Dto.DistrictDTO;
import com.example.master.services.DistrictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public List<DistrictDTO> getAllDistricts() {
        return districtService.getAllDistricts();
    }
}
