package com.example.master.controller;

import com.example.master.Dto.BatchDetailDTO;
import com.example.master.services.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    // ✅ Accepts array payload
    @PostMapping
    public ResponseEntity<List<BatchDetailDTO>> createBatches(@RequestBody List<BatchDetailDTO> dtos) {
        return ResponseEntity.ok(batchService.saveBatches(dtos));
    }

    @GetMapping
    public ResponseEntity<List<BatchDetailDTO>> getAllBatches() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }
}



//package com.example.master.controller;
//
//import com.example.master.Dto.BatchDetailDTO;
//import com.example.master.services.BatchService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/batches")
//public class BatchController {
//
//    private final BatchService batchService;
//
//    public BatchController(BatchService batchService) {
//        this.batchService = batchService;
//    }
//
//    @PostMapping
//    public ResponseEntity<BatchDetailDTO> createBatch(@RequestBody BatchDetailDTO dto) {
//        return ResponseEntity.ok(batchService.saveBatch(dto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<BatchDetailDTO>> getAllBatches() {
//        return ResponseEntity.ok(batchService.getAllBatches());
//    }
//}
