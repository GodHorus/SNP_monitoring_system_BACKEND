// DispatchDetailController.java
package com.example.master.controller;

import com.example.master.Dto.DispatchDetailDTO;
import com.example.master.model.DispatchDetail;
import com.example.master.services.DispatchDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/demand-dispatches")
public class DispatchDetailController {

    private final DispatchDetailService service;

    public DispatchDetailController(DispatchDetailService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DispatchDetail> create(@RequestBody DispatchDetailDTO dto){
        DispatchDetail d = new DispatchDetail();
        d.setDemandId(dto.demandId);
        d.setBatchNumber(dto.batchNumber);
        d.setCdpoName(dto.cdpoName);
        d.setNumberOfPackets(dto.numberOfPackets);
        d.setRemarks(dto.remarks);

        DispatchDetail saved = service.createDispatch(d);
        // ✅ return 201 CREATED with entity in body (no URI)
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DispatchDetail>> createBulk(@RequestBody List<DispatchDetailDTO> dtos) {
        List<DispatchDetail> dispatches = dtos.stream().map(dto -> {
            DispatchDetail d = new DispatchDetail();
            d.setDemandId(dto.demandId);
            d.setBatchNumber(dto.batchNumber);
            d.setCdpoName(dto.cdpoName);
            d.setNumberOfPackets(dto.numberOfPackets);
            d.setRemarks(dto.remarks);
            return d;
        }).toList();

        List<DispatchDetail> saved = service.createDispatches(dispatches);
        return ResponseEntity.status(201).body(saved);
    }


    @GetMapping("/by-demand/{demandId}")
    public ResponseEntity<List<DispatchDetail>> listByDemand(@PathVariable Long demandId){
        return ResponseEntity.ok(service.findByDemandId(demandId));
    }

}

//package com.example.master.controller;
//
//import com.example.master.Dto.DispatchDetailDTO;
//import com.example.master.services.DispatchService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/dispatch")
//public class DispatchController {
//
//    private final DispatchService dispatchService;
//
//    public DispatchController(DispatchService dispatchService) {
//        this.dispatchService = dispatchService;
//    }
//
////    @PostMapping
////    public ResponseEntity<DispatchDetailDTO> createDispatch(@RequestBody DispatchDetailDTO dto) {
////        return ResponseEntity.ok(dispatchService.saveDispatch(dto));
////    }
////
////    @GetMapping
////    public ResponseEntity<List<DispatchDetailDTO>> getAllDispatches() {
////        return ResponseEntity.ok(dispatchService.getAllDispatches());
////    }
//
//    @PostMapping
//    public ResponseEntity<?> createDispatch(@RequestBody List<DispatchDetailDTO> request) {
//        return ResponseEntity.ok(dispatchService.saveDispatchDetails(request));
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAllDispatch() {
//        return ResponseEntity.ok(dispatchService.getAllDispatchDetails());
//    }
//
//}
