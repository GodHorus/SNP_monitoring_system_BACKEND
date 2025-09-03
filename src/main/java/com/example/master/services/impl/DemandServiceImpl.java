package com.example.master.services.impl;

import com.example.master.Dto.*;
import com.example.master.event.DemandEventPublisher;
import com.example.master.exception.NotFoundException;
import com.example.master.model.*;
import com.example.master.repository.*;
import com.example.master.services.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    private final DemandRepository demandRepository;
    private final SupplierRepository supplierRepository;
    private final DistrictRepository districtRepository;
    private final CdpoRepository cdpoRepository;
    private final SectorRepository sectorRepository;
    private final DemandEventPublisher eventPublisher;

    public DemandServiceImpl(
            DemandRepository demandRepository,
            SupplierRepository supplierRepository,
            DistrictRepository districtRepository,
            CdpoRepository cdpoRepository,
            SectorRepository sectorRepository,
            DemandEventPublisher eventPublisher
    ) {
        this.demandRepository = demandRepository;
        this.supplierRepository = supplierRepository;
        this.districtRepository = districtRepository;
        this.cdpoRepository = cdpoRepository;
        this.sectorRepository = sectorRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Demand createDemand(DemandDTO dto) {
        Demand demand = new Demand();
        demand.setDescription(dto.getDescription());
        demand.setStatus("PENDING");
        demand.setFromDate(dto.getFromDate());
        demand.setToDate(dto.getToDate());
        demand.setFciId(dto.getFciId());
        demand.setFciDocs(dto.getFciDocs());
        demand.setQuantity(dto.getQuantity());
        demand.setQuantityUnit(dto.getQuantityUnit());
        demand.setDemandCategory(dto.getDemandCategory());
        demand.setDemandProduct(dto.getDemandProduct());
        demand.setBeneficery(dto.getBeneficery());
        demand.setNotes(dto.getNotes());
        demand.setCreatedAt(LocalDateTime.now());
        demand.setUpdatedAt(LocalDateTime.now());

        // Map suppliers (supplierMappings)
        List<SupplierMapping> mappings = dto.getSupplierIds().stream().map(s -> {
            SupplierMapping mapping = new SupplierMapping();
            mapping.setDemand(demand);

            // Fetch the Supplier, District, Cdpo, and Sector by ID
            Supplier supplier = supplierRepository.getReferenceById(s.getId());
            District district = districtRepository.getReferenceById(s.getDistrictId());

            mapping.setSupplier(supplier);
            mapping.setDistrict(district);

            // Map CDPOs
            List<Cdpo> cdpos = s.getCdpoIds().stream()
                    .map(cdpoId -> cdpoRepository.getReferenceById(cdpoId))
                    .collect(Collectors.toList());
            mapping.setCdpos(cdpos);

            // Map Sectors
            List<Sector> sectors = s.getSectorIds().stream()
                    .map(sectorId -> sectorRepository.getReferenceById(sectorId))
                    .collect(Collectors.toList());
            mapping.setSectors(sectors);

            return mapping;
        }).collect(Collectors.toList());
        demand.setSupplierMappings(mappings);

        // Map AWC details
        List<DemandAwcDetail> awcDetails = dto.getAwcDetails().stream().map(awcDto -> {
            DemandAwcDetail detail = new DemandAwcDetail();
            detail.setDemand(demand);

            AnganwadiCenter awc = new AnganwadiCenter();
            awc.setId(awcDto.getAwcId());
            detail.setAnganwadi(awc);

            detail.setQuantity(awcDto.getQuantity());
            detail.setType(awcDto.getType());
            return detail;
        }).collect(Collectors.toList());
        demand.setAwcDetails(awcDetails);

        Demand savedDemand = demandRepository.save(demand);
        eventPublisher.publish("NEW_DEMAND:" + savedDemand.getId());
        return savedDemand;
    }


    @Override
    public List<DemandResponseDTO> getAllDemands() {
        return demandRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Demand getDemandById(Long id) {
        return demandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Demand not found with id: " + id));
    }

    @Override
    public void deleteDemand(Long id) {
        if (!demandRepository.existsById(id)) {
            throw new NotFoundException("Demand not found with id: " + id);
        }
        demandRepository.deleteById(id);
    }

    @Override
    public Demand updateStatus(Long id, String status) {
        Demand demand = getDemandById(id);
        demand.setStatus(status);
        demand.setUpdatedAt(LocalDateTime.now());
        return demandRepository.save(demand);
    }

    @Override
    public List<DemandResponseDTO> getDemandsByStatus(String status) {
        return demandRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandResponseDTO> getPendingDemandsForFCI() {
        return getDemandsByStatus("PENDING");
    }

    @Override
    public List<DemandResponseDTO> getAcceptedDemandsForSupplier() {
        return getDemandsByStatus("FCI_ACCEPTED");
    }

    @Override
    public List<DemandResponseDTO> getManufacturedDemandsForCDPO() {
        return getDemandsByStatus("SUPPLIER_ACCEPTED");
    }

    @Override
    public List<DemandResponseDTO> getDispatchedDemandsForAWC() {
        return getDemandsByStatus("CDPO_DISPATCHED");
    }

    private DemandResponseDTO convertToDTO(Demand demand) {
        DemandResponseDTO dto = new DemandResponseDTO();
        dto.setId(demand.getId());
        dto.setDescription(demand.getDescription());
        dto.setStatus(demand.getStatus());
        dto.setFromDate(demand.getFromDate());
        dto.setToDate(demand.getToDate());
        dto.setFciId(demand.getFciId());
        dto.setFciDocs(demand.getFciDocs());
        dto.setQuantity(demand.getQuantity());
        dto.setQuantityUnit(demand.getQuantityUnit());
        dto.setDemandCategory(demand.getDemandCategory());
        dto.setDemandProduct(demand.getDemandProduct());
        dto.setBeneficery(demand.getBeneficery());
        dto.setRejectionReason(demand.getRejectionReason());
        dto.setNotes(demand.getNotes());
        dto.setCreatedAt(demand.getCreatedAt());
        dto.setUpdatedAt(demand.getUpdatedAt());
        dto.setFciAcceptedAt(demand.getFciAcceptedAt());
        dto.setFciRejectedAt(demand.getFciRejectedAt());
        dto.setSupplierAcceptedAt(demand.getSupplierAcceptedAt());
        dto.setSupplierRejectedAt(demand.getSupplierRejectedAt());
        dto.setCdpoDispatchedAt(demand.getCdpoDispatchedAt());
        dto.setAwcAcceptedAt(demand.getAwcAcceptedAt());

        // Supplier mappings
        dto.setSupplierMappings(demand.getSupplierMappings().stream().map(sm -> {
            SupplierMappingResponseDTO smDto = new SupplierMappingResponseDTO();
            smDto.setId(sm.getId());

            // Demand (basic info)
            SupplierMappingResponseDTO.DemandDTO demandDTO = new SupplierMappingResponseDTO.DemandDTO();
            demandDTO.setId(demand.getId());
            demandDTO.setName(demand.getDescription());
            smDto.setDemand(demandDTO);

            // Supplier
            SupplierMappingResponseDTO.SupplierDTO supplierDTO = new SupplierMappingResponseDTO.SupplierDTO();
            supplierDTO.setId(sm.getSupplier().getId());
            supplierDTO.setName(sm.getSupplier().getName());
            smDto.setSupplier(supplierDTO);

            // District
            SupplierMappingResponseDTO.DistrictDTO districtDTO = new SupplierMappingResponseDTO.DistrictDTO();
            districtDTO.setId(sm.getDistrict().getId());
            districtDTO.setName(sm.getDistrict().getDistrictName());
            smDto.setDistrict(districtDTO);

            // CDPOs
            smDto.setCdpos(sm.getCdpos().stream().map(cdpo -> {
                SupplierMappingResponseDTO.CdpoDTO cdpoDTO = new SupplierMappingResponseDTO.CdpoDTO();
                cdpoDTO.setId(cdpo.getId());
                cdpoDTO.setCdpoName(cdpo.getCdpoName());
                return cdpoDTO;
            }).collect(Collectors.toList()));

            // Sectors
            smDto.setSectors(sm.getSectors().stream().map(sector -> {
                SupplierMappingResponseDTO.SectorDTO sectorDTO = new SupplierMappingResponseDTO.SectorDTO();
                sectorDTO.setId(sector.getId());
                sectorDTO.setName(sector.getName());
                return sectorDTO;
            }).collect(Collectors.toList()));

            return smDto;
        }).collect(Collectors.toList()));


        // AWC details
        dto.setAwcDetails(demand.getAwcDetails().stream().map(awc -> {
            DemandAwcDetailDTO awcDto = new DemandAwcDetailDTO();
            awcDto.setAwcId(awc.getAnganwadi().getId());
            awcDto.setType(awc.getType());
            awcDto.setQuantity(awc.getQuantity());
            return awcDto;
        }).collect(Collectors.toList()));

        return dto;
    }
}
