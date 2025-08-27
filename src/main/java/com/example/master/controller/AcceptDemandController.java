package com.example.master.controller;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.services.AcceptDemandService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accept-demand")
public class AcceptDemandController {

    private final AcceptDemandService acceptDemandService;


    public AcceptDemandController(AcceptDemandService acceptDemandService) {
        this.acceptDemandService = acceptDemandService;
    }

    @PostMapping
    public AcceptDemand createAcceptDemand(@RequestBody AcceptDemandDTO dto) {
        return acceptDemandService.createAcceptDemand(dto);
    }

    @GetMapping
    public List<AcceptDemand> getAllAcceptDemands() {
        return acceptDemandService.getAllAcceptDemands();
    }
}
