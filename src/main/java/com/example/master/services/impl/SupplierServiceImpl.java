package com.example.master.services.impl;

import com.example.master.Dto.SupplierDTO;
import com.example.master.model.Supplier;
import com.example.master.repository.SupplierRepository;
import com.example.master.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    private SupplierDTO convertToDTO(Supplier supplier) {
        return new SupplierDTO(supplier.getId(), supplier.getName());
    }

    private Supplier convertToEntity(SupplierDTO dto) {
        return new Supplier(dto.getId(), dto.getName());
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = convertToEntity(supplierDTO);
        Supplier saved = supplierRepository.save(supplier);
        return convertToDTO(saved);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        return supplierRepository.findById(id)
                .map(existing -> {
                    existing.setName(supplierDTO.getName());
                    Supplier updated = supplierRepository.save(existing);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
