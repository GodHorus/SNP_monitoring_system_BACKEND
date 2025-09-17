package com.example.master.controller;

import com.example.master.Dto.PacketsRemarksDTO;
import com.example.master.services.PacketsRemarksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packets-remarks")
public class PacketsRemarksController {

    private final PacketsRemarksService service;

    public PacketsRemarksController(PacketsRemarksService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacketsRemarksDTO> create(@RequestBody PacketsRemarksDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacketsRemarksDTO> update(@PathVariable Long id, @RequestBody PacketsRemarksDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacketsRemarksDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/awc-dispatch/{awcDispatchId}")
    public ResponseEntity<List<PacketsRemarksDTO>> getByAwcDispatchId(@PathVariable Long awcDispatchId) {
        return ResponseEntity.ok(service.getByAwcDispatchId(awcDispatchId));
    }

    @GetMapping
    public ResponseEntity<List<PacketsRemarksDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
