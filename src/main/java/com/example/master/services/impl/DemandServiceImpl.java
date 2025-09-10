package com.example.master.services.impl;

import com.example.master.Dto.*;
import com.example.master.Dto.Commodity;
import com.example.master.Dto.DemandProduct;
import com.example.master.config.KeycloakUserService;
import com.example.master.event.DemandEventPublisher;
import com.example.master.exception.NotFoundException;
import com.example.master.model.*;
import com.example.master.repository.*;
import com.example.master.services.DemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    private final KeycloakUserService keycloakUserService;

    private final DemandRepository demandRepository;
    private final SupplierRepository supplierRepository;
    private final DistrictRepository districtRepository;
    private final CdpoRepository cdpoRepository;
    private final DemandEventPublisher eventPublisher;
    private final DemandCategoryRepository demandCategoryRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final FciRepository fciRepository;

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
            DemandEventPublisher eventPublisher,
            KeycloakUserService keycloakUserService
    ) {
        this.demandRepository = demandRepository;
        this.supplierRepository = supplierRepository;
        this.districtRepository = districtRepository;
        this.cdpoRepository = cdpoRepository;
        this.eventPublisher = eventPublisher;
        this.demandCategoryRepository = demandCategoryRepository;
        this.beneficiaryRepository = beneficiaryRepository;
        this.fciRepository = fciRepository;
        this.keycloakUserService = keycloakUserService;
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
                detail.setDistrict(districtRepository.findById(cdpoDto.getDistrictId())
                        .orElseThrow(() -> new RuntimeException("District not found")));
                detail.setQuantity(cdpoDto.getQuantity());
                detail.setQuantityUnits(cdpoDto.getQuantityUnits());
                detail.setBeneficiaryCount(cdpoDto.getBeneficiaryCount());
                return detail;
            }).collect(Collectors.toList());

            demand.setCdpoDetails(cdpoDetails);
        }

        // 🔹 Product Quantities
        if (dto.getProductQuantities() != null) {
            ProductQuantityRequest pqReq = dto.getProductQuantities();

//            DemandProduct demandProduct = demandProductRepository.findById(pqReq.getDemandProductId())
//                    .orElseThrow(() -> new NotFoundException("DemandProduct not found"));
            DemandProduct demandProduct = DemandProduct.getById(pqReq.getDemandProductId());

            // link product to demand
//            demandProduct.setDemand(demand);

            List<ProductCommodityQuantity> quantities = pqReq.getCommodityQuantities().entrySet().stream()
                    .map(entry -> {
                        Long commodityId = entry.getKey();
                        Double qty = entry.getValue();

//                        Commodity commodity = commodityRepository.findById(commodityId)
//                                .orElseThrow(() -> new NotFoundException("Commodity not found"));

                        Commodity commodity = Commodity.getById(commodityId);

                        ProductCommodityQuantity pcq = new ProductCommodityQuantity();
                        pcq.setDemand(demand);
                        pcq.setDemandProduct(demandProduct.getName());
                        pcq.setCommodity(commodity.getName());
                        pcq.setQuantity(qty);

                        return pcq;
                    }).toList();

            demand.setProductQuantities(quantities);

            demand.setDemandProducts((demandProduct.getName()));

            return demandRepository.save(demand);
        }

        Demand savedDemand = demandRepository.save(demand);

        eventPublisher.publish("NEW_DEMAND:" + savedDemand.getId());
        return savedDemand;
    }

    @Override
    public void updateRejectionReason(Long demandId, String rejectionReason) {
        // Find the demand by ID
        Demand demand = demandRepository.findById(demandId)
                .orElseThrow(() -> new RuntimeException("Demand not found"));

        // Update the rejection reason
        demand.setRejectionReason(rejectionReason);

        // Save the updated demand
        demandRepository.save(demand);
    }


    @Override
    public List<DemandResponseDTO> getAllDemands() {
        return demandRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DemandResponseDTO> getDemandById(Long id) {
        return demandRepository.findById(id)
                .map(this::convertToDTO);
//                .orElseThrow(() -> new NotFoundException("Demand not found with id: " + id));
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
        // Get the demand object from the repository
        Optional<Demand> demandOptional = demandRepository.findById(id);

        // Check if the demand is present
        if (demandOptional.isPresent()) {
            // Extract the existing demand entity
            Demand demand = demandOptional.get();

            // Update the status and the updated time
            demand.setStatus(status);
            demand.setUpdatedAt(LocalDateTime.now());

            // Optionally, you can update other fields here if needed
            // e.g., demand.setSomeField(newValue);

            // Save the updated entity
            return demandRepository.save(demand);
        } else {
            // Handle the case when demand is not found
            throw new RuntimeException("Demand not found with id: " + id);
        }
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

//    @Override
//    public List<DemandResponseDTO> getAcceptedDemandsForSupplier() {
//        return demandRepository.findAcceptedDemandsForSupplier()
//                .stream().map(this::convertToDTO).toList();
//    }

    @Override
    public List<DemandResponseDTO> getAcceptedDemandsForSupplier() {
        // 1. Get current Keycloak userId (UUID)
        String keycloakUserId = keycloakUserService.getCurrentUserId();

        // 2. Find supplier from DB using that UUID
        Supplier supplier = supplierRepository.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new RuntimeException("Supplier not found for user " + keycloakUserId));

        // 3. Fetch demands by supplier.id
        return demandRepository.findAcceptedDemandsForSupplier(supplier.getId())
                .stream()
                .map(this::convertToDTO)
                .toList();
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

//        List<ProductQuantityResponse> productQuantityResponses = new ArrayList<>();
        Map<String, Double> commodityQuantities = new HashMap<>();
        ProductQuantityResponse productQuantityResponse = new ProductQuantityResponse();

        for (ProductCommodityQuantity productCommodityQuantity: demand.getProductQuantities()) {
            productQuantityResponse.setProductType(productCommodityQuantity.getDemandProduct());
//            productQuantityResponses.add(productQuantityResponse);
            commodityQuantities.put(productCommodityQuantity.getCommodity(),productCommodityQuantity.getQuantity());
        }

        productQuantityResponse.setCommodityQuantities(commodityQuantities);
        dto.setProductQuantity(productQuantityResponse);



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
//        if (demand.getDistrict() != null) {
//            DistrictDTO dist = new DistrictDTO();
//            dist.setId(demand.getDistrict().getId());
//            dist.setDistrictName(demand.getDistrict().getDistrictName());
//            dto.setDistrict(dist);
//        }

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
                cdpoDto.setDistrictId(cdpo.getDistrict().getId());
                cdpoDto.setDistrictName(cdpo.getDistrict().getDistrictName());
                cdpoDto.setQuantity(cdpo.getQuantity());
                cdpoDto.setQuantityUnits(cdpo.getQuantityUnits());
                cdpoDto.setBeneficiaryCount(cdpo.getBeneficiaryCount());
                return cdpoDto;
            }).toList();
            dto.setCdpoDetails(cdpoDtos);
        }

        // 🔹 Check and conditionally set LocalDateTime fields
        if (demand.getFciAcceptedAt() != null) {
            dto.setFciAcceptedAt(demand.getFciAcceptedAt());
        }
        if (demand.getFciRejectedAt() != null) {
            dto.setFciRejectedAt(demand.getFciRejectedAt());
        }
        if (demand.getFciDispatchedAt() != null) {
            dto.setFciDispatchedAt(demand.getFciDispatchedAt());
        }
        if (demand.getSupplierAcceptedAt() != null) {
            dto.setSupplierAcceptedAt(demand.getSupplierAcceptedAt());
        }
        if (demand.getSupplierRejectedAt() != null) {
            dto.setSupplierRejectedAt(demand.getSupplierRejectedAt());
        }
        if (demand.getSupplierSelfDeclaredAt() != null) {
            dto.setSupplierSelfDeclaredAt(demand.getSupplierSelfDeclaredAt());
        }
        if (demand.getSupplierDispatchedAt() != null) {
            dto.setSupplierDispatchedAt(demand.getSupplierDispatchedAt());
        }
        if (demand.getCdpoDispatchedAt() != null) {
            dto.setCdpoDispatchedAt(demand.getCdpoDispatchedAt());
        }
        if (demand.getAwcAcceptedAt() != null) {
            dto.setAwcAcceptedAt(demand.getAwcAcceptedAt());
        }
        if (demand.getAwcDistributedAt() != null) {
            dto.setAwcDistributedAt(demand.getAwcDistributedAt());
        }

        // 🔹 Rejection reason (if present)
        if (demand.getRejectionReason() != null && !demand.getRejectionReason().isEmpty()) {
            dto.setRejectionReason(demand.getRejectionReason());
        }


        return dto;
    }

}
