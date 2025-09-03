package com.example.master.services;

import com.example.master.Dto.SupplierMappingDTO;
import com.example.master.model.SupplierMapping;

import java.util.List;

public interface SupplierMappingService {
    SupplierMapping createMapping(Long demandId, SupplierMappingDTO dto);
    List<SupplierMapping> getMappingsByDemand(Long demandId);
    void deleteMapping(Long id);
}
