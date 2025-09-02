package com.example.master.controller;

import com.example.master.Dto.AWCDistributeDTO;
import com.example.master.services.AWCDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awc-distribute")
public class AWCDistributeController {

    @Autowired
    private AWCDistributeService service;

    @PostMapping
    public ResponseEntity<AWCDistributeDTO> create(@RequestBody AWCDistributeDTO dto) {
        return ResponseEntity.ok(service.createAWCDistribute(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AWCDistributeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAWCDistributeById(id));
    }

    @GetMapping
    public ResponseEntity<List<AWCDistributeDTO>> getAll() {
        return ResponseEntity.ok(service.getAllAWCDistributes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AWCDistributeDTO> update(@PathVariable Long id, @RequestBody AWCDistributeDTO dto) {
        return ResponseEntity.ok(service.updateAWCDistribute(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAWCDistribute(id);
        return ResponseEntity.noContent().build();
    }
}
