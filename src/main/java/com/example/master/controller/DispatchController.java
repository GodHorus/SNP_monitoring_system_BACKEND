package com.example.master.controller;

import com.example.master.Dto.DispatchDetailDTO;
import com.example.master.services.DispatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    private final DispatchService dispatchService;

    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public ResponseEntity<DispatchDetailDTO> createDispatch(@RequestBody DispatchDetailDTO dto) {
        return ResponseEntity.ok(dispatchService.saveDispatch(dto));
    }

    @GetMapping
    public ResponseEntity<List<DispatchDetailDTO>> getAllDispatches() {
        return ResponseEntity.ok(dispatchService.getAllDispatches());
    }
}
