package com.example.master.controller;

import com.example.master.Dto.SiteIdentityDTO;
import com.example.master.services.SiteIdentityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/site-identity")
public class SiteIdentityController {

    private final SiteIdentityService service;

    public SiteIdentityController(SiteIdentityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteIdentityDTO> getSiteIdentity(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSiteIdentity(id));
    }

    @GetMapping
    public ResponseEntity<List<SiteIdentityDTO>> getAllSiteIdentities() {
        return ResponseEntity.ok(service.getAllSiteIdentities());
    }

    @PostMapping
    public ResponseEntity<SiteIdentityDTO> createSiteIdentity(@RequestBody SiteIdentityDTO dto) {
        return ResponseEntity.ok(service.createSiteIdentity(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteIdentityDTO> updateSiteIdentity(@PathVariable Long id, @RequestBody SiteIdentityDTO dto) {
        return ResponseEntity.ok(service.updateSiteIdentity(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiteIdentity(@PathVariable Long id) {
        service.deleteSiteIdentity(id);
        return ResponseEntity.noContent().build();
    }
}
