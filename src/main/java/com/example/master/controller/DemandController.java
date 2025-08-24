package com.example.master.controller;

import com.example.master.Dto.DemandDTO;
import com.example.master.model.Demand;
import com.example.master.services.DemandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.master.Dto.DecisionRequest;

import java.util.List;

@RestController
@RequestMapping("/api/demands")
public class DemandController {

    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    // DWCD creates demand
    @PreAuthorize("hasRole('DWCD')")
    @PostMapping
    public ResponseEntity<Demand> createDemand(@RequestBody DemandDTO dto) {
        return ResponseEntity.ok(demandService.createDemand(dto));
    }

    // Admin & DWCD can view all demands
    @PreAuthorize("hasAnyRole('ADMIN','DWCD')")
    @GetMapping
    public ResponseEntity<List<Demand>> getAllDemands() {
        List<Demand> demands = demandService.getAllDemands();
        return ResponseEntity.ok(demands);
    }

    // All roles can view a demand
    @PreAuthorize("hasAnyRole('ADMIN','DWCD','FCI','SUPPLIER','CDPO','AWC')")
    @GetMapping("/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable Long id) {
        return ResponseEntity.ok(demandService.getDemandById(id));
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemand(@PathVariable Long id) {
        demandService.deleteDemand(id);
        return ResponseEntity.noContent().build();
    }

    // FCI decision
    @PreAuthorize("hasRole('FCI')")
    @PostMapping("/{id}/fci-decision")
    public ResponseEntity<String> fciDecision(@PathVariable Long id, @RequestBody DecisionRequest request) {
        demandService.updateStatus(id, request.isAccept() ? "FCI_ACCEPTED" : "FCI_REJECTED");
        return ResponseEntity.ok(request.isAccept() ? "Demand accepted by FCI" : "Demand rejected by FCI");
    }

    // Supplier decision
    @PreAuthorize("hasRole('SUPPLIER')")
    @PostMapping("/{id}/supplier-decision")
    public ResponseEntity<String> supplierDecision(@PathVariable Long id, @RequestBody DecisionRequest request) {
        demandService.updateStatus(id, request.isAccept() ? "SUPPLIER_ACCEPTED" : "SUPPLIER_REJECTED");
        return ResponseEntity.ok(request.isAccept() ? "Supplier accepted and started manufacturing" : "Supplier rejected");
    }

    // CDPO dispatch
    @PreAuthorize("hasRole('CDPO')")
    @PostMapping("/{id}/cdpo-dispatch")
    public ResponseEntity<String> cdpoDispatch(@PathVariable Long id) {
        demandService.updateStatus(id, "CDPO_DISPATCHED");
        return ResponseEntity.ok("CDPO dispatched to AWC");
    }

    // AWC accept
    @PreAuthorize("hasRole('AWC')")
    @PostMapping("/{id}/awc-accept")
    public ResponseEntity<String> awcAccept(@PathVariable Long id) {
        demandService.updateStatus(id, "AWC_ACCEPTED");
        return ResponseEntity.ok("AWC accepted and distributed to beneficiaries");
    }
}
