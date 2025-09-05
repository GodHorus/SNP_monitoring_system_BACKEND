package com.example.master.services.impl;

import com.example.master.Dto.WarehouseDTO;
import com.example.master.model.Warehouse;
import com.example.master.repository.WarehouseRepository;
import com.example.master.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public WarehouseDTO saveWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouse_name(warehouseDTO.getWarehouseName());
        warehouse.setLocation(warehouseDTO.getLocation());

        warehouse = warehouseRepository.save(warehouse);

        return new WarehouseDTO(warehouse.getId(), warehouse.getWarehouse_name(), warehouse.getLocation());
    }

    @Override
    public WarehouseDTO getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return new WarehouseDTO(warehouse.getId(), warehouse.getWarehouse_name(), warehouse.getLocation());
    }

    @Override
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouse -> new WarehouseDTO(warehouse.getId(), warehouse.getWarehouse_name(), warehouse.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
