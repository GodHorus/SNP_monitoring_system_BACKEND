package com.example.master.controller;

import com.example.master.Dto.AWCDispatchDTO;
import com.example.master.services.AWCDispatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/awc-dispatch")
public class AWCDispatchController {

    private final AWCDispatchService awcDispatchService;

    public AWCDispatchController(AWCDispatchService awcDispatchService) {
        this.awcDispatchService = awcDispatchService;
    }

    @PostMapping
    public AWCDispatchDTO createAWCDispatch(@RequestBody AWCDispatchDTO dto) {
        return awcDispatchService.createAWCDispatch(dto);
    }

    @GetMapping
    public List<AWCDispatchDTO> getAllAWCDispatches() {
        return awcDispatchService.getAllAWCDispatches();
    }
}
