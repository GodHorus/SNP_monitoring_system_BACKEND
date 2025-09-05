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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    private final DemandCategoryRepository demandCategoryRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final FciRepository fciRepository;
    private final DemandProductRepository demandProductRepository;
    private final CommodityRepository commodityRepository;

    public DemandServiceImpl(
            DemandRepository demandRepository,
            SupplierRepository supplierRepository,
            DistrictRepository districtRepository,
            CdpoRepository cdpoRepository,
            SectorRepository sectorRepository,
            DemandCategoryRepository demandCategoryRepository,
            BeneficiaryRepository beneficiaryRepository,
            FciRepository fciRepository,
            DemandProductRepository demandProductRepository,
            CommodityRepository commodityRepository,
            DemandEventPublisher eventPublisher
    ) {
        this.demandRepository = demandRepository;
        this.supplierRepository = supplierRepository;
        this.districtRepository = districtRepository;
        this.cdpoRepository = cdpoRepository;
        this.sectorRepository = sectorRepository;
        this.eventPublisher = eventPublisher;
        this.demandCategoryRepository = demandCategoryRepository;
        this.beneficiaryRepository = beneficiaryRepository;
        this.fciRepository = fciRepository;
        this.demandProductRepository = demandProductRepository;
        this.commodityRepository = commodityRepository;
    }

    @Override
    public Demand createDemand(DemandDTO dto) {
        Demand demand = new Demand();
        demand.setDescription(dto.getDescription());
        demand.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        demand.setFromDate(dto.getFromDate());
        demand.setToDate(dto.getToDate());
        demand.setTotalDays(dto.getTotalDays());
        demand.setFciDocs(dto.getFciDocs());
        demand.setSupplierDocs(dto.getSupplierDocs());
        demand.setNotes(dto.getNotes());
        demand.setCreatedAt(LocalDateTime.now());
        demand.setUpdatedAt(LocalDateTime.now());

        // 🔹 Map DemandCategory
        if (dto.getDemandCategoryId() != null) {
            demand.setDemandCategory(demandCategoryRepository.findById(dto.getDemandCategoryId())
                    .orElseThrow(() -> new RuntimeException("DemandCategory not found")));
        }

        // 🔹 Map Beneficiary
        if (dto.getBeneficiaryId() != null) {
            demand.setBeneficery(beneficiaryRepository.findById(dto.getBeneficiaryId())
                    .orElseThrow(() -> new RuntimeException("Beneficiary not found")));
        }

        // 🔹 Map FCI
        if (dto.getFciId() != null) {
            demand.setFci(fciRepository.findById(dto.getFciId())
                    .orElseThrow(() -> new RuntimeException("FCI not found")));
        }

        // 🔹 Map Supplier
        if (dto.getSupplierId() != null) {
            demand.setSupplier(supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found")));
        }

        // 🔹 Map District
        if (dto.getDistrictId() != null) {
            demand.setDistrict(districtRepository.findById(dto.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found")));
        }

        // 🔹 Map CDPO Details
        if (dto.getCdpoDetails() != null && !dto.getCdpoDetails().isEmpty()) {
            List<DemandCdpoDetail> cdpoDetails = dto.getCdpoDetails().stream().map(cdpoDto -> {
                DemandCdpoDetail detail = new DemandCdpoDetail();
                detail.setDemand(demand);
                detail.setCdpo(cdpoRepository.findById(cdpoDto.getCdpoId())
                        .orElseThrow(() -> new RuntimeException("CDPO not found")));
                detail.setQuantity(cdpoDto.getQuantity());
                detail.setQuantityUnits(cdpoDto.getQuantityUnits());
                detail.setBeneficiaryCount(cdpoDto.getBeneficiaryCount());
                return detail;
            }).collect(Collectors.toList());

            demand.setCdpoDetails(cdpoDetails);
        }


        // 🔹 Product + Commodity Quantities
        // 🔹 Product + Commodity Quantities
        if (dto.getProductQuantities() != null) {
            ProductQuantityRequest pqReq = dto.getProductQuantities();

            // Step 1: Fetch DemandProduct by ID
            DemandProduct demandProduct = demandProductRepository.findById(pqReq.getDemandProductId())
                    .orElseThrow(() -> new RuntimeException("DemandProduct not found: " + pqReq.getDemandProductId()));

            // Step 2: Prepare the list of ProductCommodityQuantity objects
            Map<Long, Double> commodityQuantities = pqReq.getCommodityQuantities();

            // Step 3: Loop through the commodity quantities and update existing or add new ones
            for (Map.Entry<Long, Double> entry : commodityQuantities.entrySet()) {
                Commodity commodity = commodityRepository.findById(entry.getKey())
                        .orElseThrow(() -> new RuntimeException("Commodity not found: " + entry.getKey()));

                // Check if this commodity already exists in the DemandProduct's productQuantities
                ProductCommodityQuantity existingPcq = demandProduct.getProductQuantities().stream()
                        .filter(pcq -> pcq.getCommodity().getId().equals(commodity.getId()))
                        .findFirst()
                        .orElse(null);

                if (existingPcq != null) {
                    // If it exists, update the quantity
                    existingPcq.setQuantity(entry.getValue());
                } else {
                    // If it does not exist, create a new ProductCommodityQuantity
                    ProductCommodityQuantity newPcq = new ProductCommodityQuantity();
                    newPcq.setCommodity(commodity);
                    newPcq.setQuantity(entry.getValue());
                    newPcq.setDemandProduct(demandProduct);
                    demandProduct.getProductQuantities().add(newPcq); // Add to existing list
                }
            }

            // Step 4: Remove any orphaned ProductCommodityQuantities (those that are not in the request)
            demandProduct.getProductQuantities().removeIf(pcq ->
                    !commodityQuantities.containsKey(pcq.getCommodity().getId())
                            && pcq.getDemandProduct().equals(demandProduct));

            // Step 5: Persist the updated DemandProduct
            demand.setDemandProducts(Collections.singletonList(demandProduct));
        }


//        // Map suppliers (supplierMappings)
//        List<SupplierMapping> mappings = dto.getSupplierIds().stream().map(s -> {
//            SupplierMapping mapping = new SupplierMapping();
//            mapping.setDemand(demand);
//
//            // Fetch the Supplier, District, Cdpo, and Sector by ID
//            Supplier supplier = supplierRepository.getReferenceById(s.getId());
//            District district = districtRepository.getReferenceById(s.getDistrictId());
//
//            mapping.setSupplier(supplier);
//            mapping.setDistrict(district);
//
//            // Map CDPOs
//            List<Cdpo> cdpos = s.getCdpoIds().stream()
//                    .map(cdpoId -> cdpoRepository.getReferenceById(cdpoId))
//                    .collect(Collectors.toList());
//            mapping.setCdpos(cdpos);
//
//            // Map Sectors
//            List<Sector> sectors = s.getSectorIds().stream()
//                    .map(sectorId -> sectorRepository.getReferenceById(sectorId))
//                    .collect(Collectors.toList());
//            mapping.setSectors(sectors);
//
//            return mapping;
//        }).collect(Collectors.toList());
//        demand.setSupplierMappings(mappings);
//
//        // Map AWC details
//        List<DemandAwcDetail> awcDetails = dto.getAwcDetails().stream().map(awcDto -> {
//            DemandAwcDetail detail = new DemandAwcDetail();
//            detail.setDemand(demand);
//
//            AnganwadiCenter awc = new AnganwadiCenter();
//            awc.setId(awcDto.getAwcId());
//            detail.setAnganwadi(awc);
//
//            detail.setQuantity(awcDto.getQuantity());
//            detail.setType(awcDto.getType());
//            return detail;
//        }).collect(Collectors.toList());
//        demand.setAwcDetails(awcDetails);

        Demand savedDemand = demandRepository.save(demand);
        eventPublisher.publish("NEW_DEMAND:" + savedDemand.getId());
        return savedDemand;
    }

//    @Override
//    public Demand updateQuantity(Long demandId, Integer newQuantity) {
//        // Find the demand by ID
//        Demand demand = demandRepository.findById(demandId)
//                .orElseThrow(() -> new RuntimeException("Demand not found"));
//
//        // Update the quantity
//        demand.setQuantity(newQuantity);
//
//        // Save the updated demand
//        return demandRepository.save(demand);
//    }


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
        return demandRepository.findPendingAndAcceptedDemandsForFci()
                .stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<DemandResponseDTO> getAcceptedDemandsForSupplier() {
        return demandRepository.findAcceptedDemandsForSupplier()
                .stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<DemandResponseDTO> getManufacturedDemandsForCDPO() {
        return demandRepository.findDemandsForCdpo()
                .stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<DemandResponseDTO> getDispatchedDemandsForAWC() {
        return demandRepository.findDemandsForAwc()
                .stream().map(this::convertToDTO).toList();
    }


    private DemandResponseDTO convertToDTO(Demand demand) {
        DemandResponseDTO dto = new DemandResponseDTO();
        dto.setId(demand.getId());
        dto.setDescription(demand.getDescription());
        dto.setStatus(demand.getStatus());
        dto.setFromDate(demand.getFromDate());
        dto.setToDate(demand.getToDate());
        dto.setTotalDays(demand.getTotalDays());
        dto.setNotes(demand.getNotes());
        dto.setFciDocs(demand.getFciDocs());
        dto.setSupplierDocs(demand.getSupplierDocs());
        dto.setCreatedAt(demand.getCreatedAt());
        dto.setUpdatedAt(demand.getUpdatedAt());

        // 🔹 demand category
        if (demand.getDemandCategory() != null) {
            DemandCategoryDTO cat = new DemandCategoryDTO();
            cat.setId(demand.getDemandCategory().getId());
            cat.setCategory(demand.getDemandCategory().getCategory());
            dto.setDemandCategory(cat);
        }

        // 🔹 beneficiary
        if (demand.getBeneficery() != null) {
            BeneficiaryDTO ben = new BeneficiaryDTO();
            ben.setId(demand.getBeneficery().getId());
            ben.setBeneficiaryName(demand.getBeneficery().getBeneficiaryName());
            dto.setBeneficiary(ben);
        }

        // 🔹 supplier
        if (demand.getSupplier() != null) {
            SupplierDTO sup = new SupplierDTO();
            sup.setId(demand.getSupplier().getId());
            sup.setName(demand.getSupplier().getName());
            dto.setSupplier(sup);
        }

        // 🔹 district
        if (demand.getDistrict() != null) {
            DistrictDTO dist = new DistrictDTO();
            dist.setId(demand.getDistrict().getId());
            dist.setDistrictName(demand.getDistrict().getDistrictName());
            dto.setDistrict(dist);
        }

        // 🔹 fci
        if (demand.getFci() != null) {
            FciDTO fci = new FciDTO();
            fci.setId(demand.getFci().getId());
            fci.setName(demand.getFci().getName());
            dto.setFci(fci);
        }

        // 🔹 cdpo details
        if (demand.getCdpoDetails() != null) {
            List<DemandCdpoDetailResponseDTO> cdpoDtos = demand.getCdpoDetails().stream().map(cdpo -> {
                DemandCdpoDetailResponseDTO cdpoDto = new DemandCdpoDetailResponseDTO();
                cdpoDto.setId(cdpo.getId());
                cdpoDto.setCdpoId(cdpo.getCdpo().getId());
                cdpoDto.setCdpoName(cdpo.getCdpo().getCdpoName());
                cdpoDto.setQuantity(cdpo.getQuantity());
                cdpoDto.setQuantityUnits(cdpo.getQuantityUnits());
                cdpoDto.setBeneficiaryCount(cdpo.getBeneficiaryCount());
                return cdpoDto;
            }).toList();
            dto.setCdpoDetails(cdpoDtos);
        }

        // 🔹 product + commodities
        if (demand.getDemandProducts() != null && !demand.getDemandProducts().isEmpty()) {
            DemandProduct product = demand.getDemandProducts().get(0); // only one
            ProductQuantityResponse pqDto = new ProductQuantityResponse();
            pqDto.setDemandProductId(product.getId());
            pqDto.setProductType(product.getType());

            Map<String, Double> commodities = product.getProductQuantities().stream()
                    .collect(Collectors.toMap(
                            q -> q.getCommodity().getName(),
                            ProductCommodityQuantity::getQuantity
                    ));
            pqDto.setCommodityQuantities(commodities);

            dto.setProductQuantity(pqDto);
        }

        return dto;
    }

}
