package com.example.master.controller;

import com.example.master.Dto.SupplierMappingDTO;
import com.example.master.model.SupplierMapping;
import com.example.master.services.SupplierMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier-mappings")
public class SupplierMappingController {

    private final SupplierMappingService supplierMappingService;

    public SupplierMappingController(SupplierMappingService supplierMappingService) {
        this.supplierMappingService = supplierMappingService;
    }

    @PostMapping("/{demandId}")
    public ResponseEntity<SupplierMapping> createMapping(
            @PathVariable Long demandId,
            @RequestBody SupplierMappingDTO dto
    ) {
        return ResponseEntity.ok(supplierMappingService.createMapping(demandId, dto));
    }

    @GetMapping("/{demandId}")
    public ResponseEntity<List<SupplierMapping>> getMappings(@PathVariable Long demandId) {
        return ResponseEntity.ok(supplierMappingService.getMappingsByDemand(demandId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Long id) {
        supplierMappingService.deleteMapping(id);
        return ResponseEntity.noContent().build();
    }
}
