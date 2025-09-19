// BatchDetailController.java
package com.example.master.controller;

import com.example.master.Dto.BatchDetailDTO;
import com.example.master.model.BatchDetail;
import com.example.master.services.BatchDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchDetailController {

    private final BatchDetailService service;

    public BatchDetailController(BatchDetailService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<BatchDetail> create(@RequestBody BatchDetailDTO dto){
        BatchDetail b = new BatchDetail(dto.demandId, dto.type, dto.quantity, dto.qrCode);
        BatchDetail saved = service.createBatch(b);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/by-demand/{demandId}")
    public ResponseEntity<List<BatchDetail>> listByDemand(@PathVariable Long demandId) {
        return ResponseEntity.ok(service.findByDemandId(demandId));
    }
}
