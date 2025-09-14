package com.example.master.controller;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.services.AcceptDemandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accept-demand")
public class AcceptDemandController {

    private final AcceptDemandService acceptDemandService;


    public AcceptDemandController(AcceptDemandService acceptDemandService) {
        this.acceptDemandService = acceptDemandService;
    }

//    @PostMapping
//    public AcceptDemand createAcceptDemand(@RequestBody AcceptDemandDTO dto) {
//        return acceptDemandService.createAcceptDemand(dto);
//    }

//    @PostMapping()
//    public ResponseEntity<AcceptDemand> createAcceptDemand(
//            @RequestBody AcceptDemandDTO dto) {
//        return ResponseEntity.ok(acceptDemandService.createAcceptDemand(dto));
//    }

//    @PostMapping("/bulk")
    @PostMapping
    public ResponseEntity<List<AcceptDemand>> createAcceptDemands(@RequestBody List<AcceptDemandDTO> dtos) {
        return ResponseEntity.ok(acceptDemandService.createAcceptDemands(dtos));
    }

    @GetMapping("/by-demand/{demandId}")
    public ResponseEntity<AcceptDemandDTO> getByDemandId(@PathVariable Long demandId) {
        return acceptDemandService.getAcceptDemandByDemandId(demandId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AcceptDemandDTO>> getAllAcceptDemands() {
        return ResponseEntity.ok(acceptDemandService.getAllAcceptDemands());
    }


//    @GetMapping
//    public List<AcceptDemand> getAllAcceptDemands() {
//        return acceptDemandService.getAllAcceptDemands();
//    }
}
