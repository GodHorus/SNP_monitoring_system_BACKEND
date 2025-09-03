package com.example.master.services.impl;

import com.example.master.Dto.SupplierMappingDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.*;
import com.example.master.repository.*;
import com.example.master.services.SupplierMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierMappingServiceImpl implements SupplierMappingService {

    private final SupplierMappingRepository supplierMappingRepository;
    private final SupplierRepository supplierRepository;
    private final DistrictRepository districtRepository;
    private final CdpoRepository cdpoRepository;
    private final SectorRepository sectorRepository;
    private final DemandRepository demandRepository;

    public SupplierMappingServiceImpl(
            SupplierMappingRepository supplierMappingRepository,
            SupplierRepository supplierRepository,
            DistrictRepository districtRepository,
            CdpoRepository cdpoRepository,
            SectorRepository sectorRepository,
            DemandRepository demandRepository
    ) {
        this.supplierMappingRepository = supplierMappingRepository;
        this.supplierRepository = supplierRepository;
        this.districtRepository = districtRepository;
        this.cdpoRepository = cdpoRepository;
        this.sectorRepository = sectorRepository;
        this.demandRepository = demandRepository;
    }

    @Override
    public SupplierMapping createMapping(Long demandId, SupplierMappingDTO dto) {
        Demand demand = demandRepository.findById(demandId)
                .orElseThrow(() -> new NotFoundException("Demand not found"));

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        District district = districtRepository.findById(dto.getDistrictId())
                .orElseThrow(() -> new NotFoundException("District not found"));

        List<Cdpo> cdpos = cdpoRepository.findAllById(dto.getCdpoIds());
        List<Sector> sectors = sectorRepository.findAllById(dto.getSectorIds());

        SupplierMapping mapping = new SupplierMapping();
        mapping.setDemand(demand);
        mapping.setSupplier(supplier);
        mapping.setDistrict(district);
        mapping.setCdpos(cdpos);
        mapping.setSectors(sectors);

        return supplierMappingRepository.save(mapping);
    }

    @Override
    public List<SupplierMapping> getMappingsByDemand(Long demandId) {
        return supplierMappingRepository.findByDemandId(demandId);
    }

    @Override
    public void deleteMapping(Long id) {
        if (!supplierMappingRepository.existsById(id)) {
            throw new NotFoundException("Supplier mapping not found");
        }
        supplierMappingRepository.deleteById(id);
    }
}
