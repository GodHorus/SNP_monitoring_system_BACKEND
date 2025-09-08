package com.example.master.controller;

import com.example.master.Dto.Commodity;
import com.example.master.Dto.CommodityDTO;
import com.example.master.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commodities")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    // Create commodity
//    @PostMapping
//    public CommodityDTO createCommodity(@RequestBody CommodityDTO commodityDTO) {
//        return commodityService.createCommodity(commodityDTO);
//    }

    // Get all commodities
    @GetMapping
    public ResponseEntity<Commodity[]> getAllCommodities() {
//        return commodityService.getAllCommodities();
        return new ResponseEntity<>(com.example.master.Dto.Commodity.values(), HttpStatus.OK);
    }

    // Get commodity by id
    @GetMapping("/{id}")
    public CommodityDTO getCommodityById(@PathVariable Long id) {
        return commodityService.getCommodityById(id);
    }

    // Update commodity
    @PutMapping
    public CommodityDTO updateCommodity(@RequestBody CommodityDTO commodityDTO) {
        return commodityService.updateCommodity(commodityDTO);
    }

    // Delete commodity
    @DeleteMapping("/{id}")
    public boolean deleteCommodity(@PathVariable Long id) {
        return commodityService.deleteCommodity(id);
    }
}
