package com.example.master.controller;

import com.example.master.Dto.SectorDTO;
import com.example.master.services.SectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping
    public ResponseEntity<SectorDTO> createSector(@RequestBody SectorDTO sectorDTO) {
        return ResponseEntity.ok(sectorService.createSector(sectorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorDTO> updateSector(@PathVariable Long id, @RequestBody SectorDTO sectorDTO) {
        return ResponseEntity.ok(sectorService.updateSector(id, sectorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> getSectorById(@PathVariable Long id) {
        return ResponseEntity.ok(sectorService.getSectorById(id));
    }

    @GetMapping("/cdpo/{cdpoId}")
    public ResponseEntity<List<SectorDTO>> getSectorsByCdpo(@PathVariable Long cdpoId) {
        return ResponseEntity.ok(sectorService.getAllSectorsByCdpo(cdpoId));
    }

    @GetMapping
    public ResponseEntity<List<SectorDTO>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }
}
