package com.example.master.controller;

import com.example.master.Dto.AWCAcceptDemandDTO;
import com.example.master.services.AWCAcceptDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awc-accept-demand")
public class AWCAcceptDemandController {

    @Autowired
    private AWCAcceptDemandService service;

    // Single POST
    @PostMapping
    public ResponseEntity<AWCAcceptDemandDTO> create(@RequestBody AWCAcceptDemandDTO dto) {
        return ResponseEntity.ok(service.createAcceptDemand(dto));
    }

    // Batch POST
    @PostMapping("/batch")
    public ResponseEntity<List<AWCAcceptDemandDTO>> createBatch(@RequestBody List<AWCAcceptDemandDTO> dtos) {
        return ResponseEntity.ok(service.createAcceptDemands(dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AWCAcceptDemandDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAcceptDemandById(id));
    }

    @GetMapping
    public ResponseEntity<List<AWCAcceptDemandDTO>> getAll() {
        return ResponseEntity.ok(service.getAllAcceptDemands());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AWCAcceptDemandDTO> update(@PathVariable Long id, @RequestBody AWCAcceptDemandDTO dto) {
        return ResponseEntity.ok(service.updateAcceptDemand(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAcceptDemand(id);
        return ResponseEntity.noContent().build();
    }
}
