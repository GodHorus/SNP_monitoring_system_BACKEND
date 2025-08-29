package com.example.master.controller;

import com.example.master.Dto.FciDTO;
import com.example.master.services.FciService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fci")
public class FciController {

    private final FciService fciService;

    public FciController(FciService fciService) {
        this.fciService = fciService;
    }

    @GetMapping
    public ResponseEntity<List<FciDTO>> getAllFci() {
        return ResponseEntity.ok(fciService.getAllFci());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FciDTO> getFciById(@PathVariable Long id) {
        FciDTO dto = fciService.getFciById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FciDTO> createFci(@RequestBody FciDTO fciDTO) {
        return ResponseEntity.ok(fciService.createFci(fciDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FciDTO> updateFci(@PathVariable Long id, @RequestBody FciDTO fciDTO) {
        FciDTO updated = fciService.updateFci(id, fciDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFci(@PathVariable Long id) {
        fciService.deleteFci(id);
        return ResponseEntity.noContent().build();
    }
}
