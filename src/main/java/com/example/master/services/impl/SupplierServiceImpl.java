package com.example.master.services.impl;

import com.example.master.Dto.SupplierDTO;
import com.example.master.model.Supplier;
import com.example.master.repository.SupplierRepository;
import com.example.master.services.SupplierService;
import com.example.master.config.KeycloakUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final KeycloakUserService keycloakUserService;

    public SupplierServiceImpl(SupplierRepository supplierRepository,
                               KeycloakUserService keycloakUserService) {
        this.supplierRepository = supplierRepository;
        this.keycloakUserService = keycloakUserService;
    }

    private SupplierDTO convertToDTO(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getKeycloakUserId()
        );
    }

    private Supplier convertToEntity(SupplierDTO dto) {
        return new Supplier(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getKeycloakUserId()
        );
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
    @Transactional
    public SupplierDTO createSupplier(SupplierDTO supplierDTO, String password, String role, String firstName, String lastName) {
        // 1. Create Supplier in Keycloak
        String keycloakUserId = keycloakUserService.createUser(
                supplierDTO.getName(),
                supplierDTO.getEmail(),
                password,
                role,
                firstName,
                lastName
        );

        // 2. Save Supplier in DB
        Supplier supplier = convertToEntity(supplierDTO);
        supplier.setKeycloakUserId(keycloakUserId);

        Supplier saved = supplierRepository.save(supplier);
        return convertToDTO(saved);
    }

    @Override
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        return supplierRepository.findById(id)
                .map(existing -> {
                    existing.setName(supplierDTO.getName());
                    existing.setEmail(supplierDTO.getEmail());
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
