package com.example.master.controller;

import com.example.master.Dto.CdpoDTO;
import com.example.master.services.CdpoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cdpos")
public class CdpoController {

    private final CdpoService cdpoService;

    public CdpoController(CdpoService cdpoService) {
        this.cdpoService = cdpoService;
    }

    @GetMapping
    public ResponseEntity<List<CdpoDTO>> getAllCdpos() {
        return ResponseEntity.ok(cdpoService.getAllCdpos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CdpoDTO> getCdpoById(@PathVariable Long id) {
        return ResponseEntity.ok(cdpoService.getCdpoById(id));
    }

    @PostMapping
    public ResponseEntity<CdpoDTO> createCdpo(@RequestBody CdpoDTO cdpoDTO) {
        return ResponseEntity.ok(cdpoService.createCdpo(cdpoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CdpoDTO> updateCdpo(@PathVariable Long id, @RequestBody CdpoDTO cdpoDTO) {
        return ResponseEntity.ok(cdpoService.updateCdpo(id, cdpoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCdpo(@PathVariable Long id) {
        cdpoService.deleteCdpo(id);
        return ResponseEntity.noContent().build();
    }
}
