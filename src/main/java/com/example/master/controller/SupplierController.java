package com.example.master.controller;

import com.example.master.Dto.SupplierCreateRequest;
import com.example.master.Dto.SupplierDTO;
import com.example.master.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Get all suppliers
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // Get supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplier = supplierService.getSupplierById(id);
        return supplier != null ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
    }

    // Create supplier (with Keycloak user)
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(
            @RequestBody SupplierCreateRequest request) {

        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName(request.getName());
        supplierDTO.setEmail(request.getEmail());

        SupplierDTO created = supplierService.createSupplier(
                supplierDTO,
                request.getPassword(),
                request.getRole(),
                request.getFirstName(),
                request.getLastName()
        );

        return ResponseEntity.ok(created);
    }

    // Update supplier
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(
            @PathVariable Long id,
            @RequestBody SupplierDTO supplierDTO) {

        SupplierDTO updated = supplierService.updateSupplier(id, supplierDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Delete supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
