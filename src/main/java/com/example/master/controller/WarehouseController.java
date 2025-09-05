package com.example.master.controller;

import com.example.master.Dto.WarehouseDTO;
import com.example.master.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public WarehouseDTO saveWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.saveWarehouse(warehouseDTO);
    }

    @GetMapping("/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @GetMapping
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
    }
}
