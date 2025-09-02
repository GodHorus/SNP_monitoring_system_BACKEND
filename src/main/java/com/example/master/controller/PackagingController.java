package com.example.master.controller;

import com.example.master.Dto.PackagingDetailDTO;
import com.example.master.services.PackagingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packaging")
public class PackagingController {

    private final PackagingService packagingService;

    public PackagingController(PackagingService packagingService) {
        this.packagingService = packagingService;
    }

    @PostMapping
    public ResponseEntity<List<PackagingDetailDTO>> createPackaging(@RequestBody List<PackagingDetailDTO> dtoList) {
        return ResponseEntity.ok(packagingService.savePackaging(dtoList));
    }

    @GetMapping
    public ResponseEntity<List<PackagingDetailDTO>> getAllPackaging() {
        return ResponseEntity.ok(packagingService.getAllPackaging());
    }
}
