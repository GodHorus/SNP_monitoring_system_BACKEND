package com.example.master.controller;

import com.example.master.Dto.AnganwadiCenterDTO;
import com.example.master.services.AnganwadiCenterService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/awc")
public class AnganwadiCenterController {

    private final AnganwadiCenterService service;

    public AnganwadiCenterController(AnganwadiCenterService service) {
        this.service = service;
    }

    @GetMapping
    public Page<AnganwadiCenterDTO> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        String[] sortParts = sort.split(",");
        Sort.Direction dir = sortParts.length > 1 && "desc".equalsIgnoreCase(sortParts[1])
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortParts[0]));
        return service.list(pageable);
    }

    @GetMapping("/{id}")
    public AnganwadiCenterDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<AnganwadiCenterDTO> create( @RequestBody AnganwadiCenterDTO dto) {
        AnganwadiCenterDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public AnganwadiCenterDTO update(@PathVariable Long id,  @RequestBody AnganwadiCenterDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
