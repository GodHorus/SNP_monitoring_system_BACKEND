package com.example.master.controller;

import com.example.master.Dto.AWCDispatchDTO;
import com.example.master.services.AWCDispatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awc-dispatches")
public class AWCDispatchController {

    private final AWCDispatchService dispatchService;

    public AWCDispatchController(AWCDispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public ResponseEntity<AWCDispatchDTO> create(@RequestBody AWCDispatchDTO dto) {
        return ResponseEntity.ok(dispatchService.createDispatch(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AWCDispatchDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dispatchService.getDispatchById(id));
    }

    @GetMapping
    public ResponseEntity<List<AWCDispatchDTO>> getAll() {
        return ResponseEntity.ok(dispatchService.getAllDispatches());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AWCDispatchDTO> update(@PathVariable Long id, @RequestBody AWCDispatchDTO dto) {
        return ResponseEntity.ok(dispatchService.updateDispatch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dispatchService.deleteDispatch(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<AWCDispatchDTO>> createBulk(@RequestBody List<AWCDispatchDTO> dtos) {
        return ResponseEntity.ok(dispatchService.createDispatches(dtos));
    }
}
