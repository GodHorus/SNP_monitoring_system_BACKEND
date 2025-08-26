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
    public ResponseEntity<PackagingDetailDTO> createPackaging(@RequestBody PackagingDetailDTO dto) {
        return ResponseEntity.ok(packagingService.savePackaging(dto));
    }

    @GetMapping
    public ResponseEntity<List<PackagingDetailDTO>> getAllPackaging() {
        return ResponseEntity.ok(packagingService.getAllPackaging());
    }
}
