package com.example.master.services;

import com.example.master.Dto.WarehouseDTO;

import java.util.List;

public interface WarehouseService {

    WarehouseDTO saveWarehouse(WarehouseDTO warehouseDTO);

    WarehouseDTO getWarehouseById(Long id);

    List<WarehouseDTO> getAllWarehouses();

    void deleteWarehouse(Long id);
}
