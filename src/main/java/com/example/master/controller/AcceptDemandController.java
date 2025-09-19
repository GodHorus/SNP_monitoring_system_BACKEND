package com.example.master.controller;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.services.AcceptDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accept-demands")
public class AcceptDemandController {

    @Autowired
    private AcceptDemandService service;

    @PostMapping
    public AcceptDemandDTO create(@RequestBody AcceptDemandDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/by-demand/{demandId}")
    public List<AcceptDemandDTO> getByDemandId(@PathVariable Long demandId) {
        return service.getByDemandId(demandId);
    }
}
