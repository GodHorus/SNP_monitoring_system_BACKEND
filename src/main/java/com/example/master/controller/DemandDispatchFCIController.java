package com.example.master.controller;

import com.example.master.Dto.DemandDispatchFCIDTO;
import com.example.master.services.DemandDispatchFCIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demand-dispatch")
public class DemandDispatchFCIController {

    @Autowired
    private DemandDispatchFCIService demandDispatchFCIService;

    @PostMapping
    public DemandDispatchFCIDTO createDemandDispatch(@RequestBody DemandDispatchFCIDTO demandDispatchFCIDTO) {
        return demandDispatchFCIService.createDemandDispatch(demandDispatchFCIDTO);
    }
}
