// PackagingDetailController.java
package com.example.master.controller;

import com.example.master.Dto.PackagingDetailDTO;
import com.example.master.model.PackagingDetail;
import com.example.master.services.PackagingDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/packaging")
public class PackagingDetailController {

    private final PackagingDetailService service;

    public PackagingDetailController(PackagingDetailService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<PackagingDetail> create(@RequestBody PackagingDetailDTO dto){
        PackagingDetail p = new PackagingDetail();
        p.setDemandId(dto.demandId);
        p.setBatchNumber(dto.batchNumber);
        p.setType(dto.type);
        p.setAvailableStock(dto.availableStock);
        p.setPacketSize(dto.packetSize);
        p.setUnit(dto.unit);
        p.setPackets(dto.packets);
        p.setRemainingStock(dto.remainingStock);

        PackagingDetail saved = service.createPackaging(p);
        return ResponseEntity.status(201).body(saved);
    }


    @PostMapping("/bulk")
    public ResponseEntity<List<PackagingDetail>> createBulk(@RequestBody List<PackagingDetailDTO> dtos) {
        List<PackagingDetail> packagings = dtos.stream().map(dto -> {
            PackagingDetail p = new PackagingDetail();
            p.setDemandId(dto.demandId);
            p.setBatchNumber(dto.batchNumber);
            p.setType(dto.type);
            p.setAvailableStock(dto.availableStock);
            p.setPacketSize(dto.packetSize);
            p.setUnit(dto.unit);
            p.setPackets(dto.packets);
            p.setRemainingStock(dto.remainingStock);
            return p;
        }).toList();

        List<PackagingDetail> saved = service.createPackagings(packagings);
        return ResponseEntity.status(201).body(saved);
    }


    @GetMapping("/by-demand/{demandId}")
    public ResponseEntity<List<PackagingDetail>> listByDemand(@PathVariable Long demandId){
        return ResponseEntity.ok(service.findByDemandId(demandId));
    }
}
