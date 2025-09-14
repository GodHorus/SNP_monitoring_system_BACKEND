package com.example.master.services.impl;

import com.example.master.Dto.*;
import com.example.master.config.KeycloakUserService;
import com.example.master.model.*;
import com.example.master.repository.*;
import com.example.master.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientDetailRepository ingredientRepo;
    private final BatchDetailRepository batchRepo;
    private final LabReportRepository labReportRepo;
    private final DemandRepository demandRepo;
    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;

    public IngredientServiceImpl(IngredientDetailRepository ingredientRepo,
                                 BatchDetailRepository batchRepo,
                                 LabReportRepository labReportRepo,
                                 DemandRepository demandRepo, KeycloakUserService keycloakUserService, UserRepository userRepository) {
        this.ingredientRepo = ingredientRepo;
        this.batchRepo = batchRepo;
        this.labReportRepo = labReportRepo;
        this.demandRepo = demandRepo;
        this.keycloakUserService = keycloakUserService;
        this.userRepository = userRepository;
    }

    // Helper method to generate batch number
    private String generateNextBatchNo() {
        String lastBatchNo = ingredientRepo.findLastBatchNo();
        int nextNumber = 1;
        if (lastBatchNo != null && lastBatchNo.startsWith("B-")) {
            String numberPart = lastBatchNo.substring(2);
            nextNumber = Integer.parseInt(numberPart) + 1;
        }
        return "B-" + nextNumber;
    }

    @Override
    public IngredientDetailDTO saveIngredient(IngredientDetailDTO dto) {
        Demand demand = demandRepo.findById(dto.getDemandId())
                .orElseThrow(() -> new RuntimeException("Demand not found"));

        IngredientDetail ingredient = dto.getId() != null ?
                ingredientRepo.findById(dto.getId()).orElse(new IngredientDetail()) :
                new IngredientDetail();

        ingredient.setType(dto.getType());
        ingredient.setName(dto.getName());
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setUnit(dto.getUnit());
        ingredient.setDemand(demand);
//        ingredient.setTotal(dto.getTotal());

        if (dto.getBatchId() != null) {
            BatchDetail batch = batchRepo.findById(dto.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch not found"));
            ingredient.setBatchDetail(batch);
            ingredient.setBatchNo("B-" + batch.getId());
        }

        ingredient = ingredientRepo.save(ingredient);

        dto.setId(ingredient.getId());
        dto.setBatchNo(ingredient.getBatchNo());
        return dto;
    }


    //    @Override
//    public List<IngredientDetailDTO> saveIngredients(List<IngredientDetailDTO> dtos) {
//        return dtos.stream().map(this::saveIngredient).collect(Collectors.toList());
//    }
    @Override
    @Transactional
    public List<IngredientDetailDTO> saveIngredients(List<IngredientDetailDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            throw new RuntimeException("Ingredient list cannot be empty");
        }

        // ✅ Step 1: Generate unique batch number once
        String batchNo = generateNextBatchNo();

        // ✅ Step 2: Create one BatchDetail row
        BatchDetail batch = new BatchDetail();
        batch.setQrCode("QR-" + batchNo);  // You can change how QR is generated
        batch = batchRepo.save(batch);

        // ✅ Step 3: Save all ingredients linked to the same batch
        BatchDetail finalBatch = batch;
        List<IngredientDetail> ingredients = dtos.stream().map(dto -> {
            Demand demand = demandRepo.findById(dto.getDemandId())
                    .orElseThrow(() -> new RuntimeException("Demand not found"));

            IngredientDetail ingredient = new IngredientDetail();
            ingredient.setType(dto.getType());
            ingredient.setName(dto.getName());
            ingredient.setQuantity(dto.getQuantity());
            ingredient.setUnit(dto.getUnit());
            ingredient.setDemand(demand);

            // link with batch
            ingredient.setBatchDetail(finalBatch);
            ingredient.setBatchNo(batchNo);

            return ingredient;
        }).collect(Collectors.toList());

        ingredients = ingredientRepo.saveAll(ingredients);

        // ✅ Step 4: Convert back to DTO
        BatchDetail finalBatch1 = batch;
        return ingredients.stream().map(ing -> {
            IngredientDetailDTO dto = new IngredientDetailDTO();
            dto.setId(ing.getId());
            dto.setType(ing.getType());
            dto.setName(ing.getName());
            dto.setQuantity(ing.getQuantity());
            dto.setUnit(ing.getUnit());
            dto.setBatchNo(ing.getBatchNo());
            dto.setBatchId(finalBatch1.getId());
            dto.setDemandId(ing.getDemand().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<IngredientDetailDTO> getAllIngredients() {
        return ingredientRepo.findAll().stream().map(ing -> {
            IngredientDetailDTO dto = new IngredientDetailDTO();
            dto.setId(ing.getId());
            dto.setType(ing.getType());
            dto.setName(ing.getName());
            dto.setQuantity(ing.getQuantity());
            dto.setUnit(ing.getUnit());
            dto.setBatchNo(ing.getBatchNo());
            dto.setDemandId(ing.getDemand().getId());

            // ✅ Map BatchDetail
            BatchDetail batch = ing.getBatchDetail();
            if (batch != null) {
                BatchDetailDTO batchDTO = new BatchDetailDTO();
                batchDTO.setId(batch.getId());
                batchDTO.setQrCode(batch.getQrCode());
                dto.setBatchDetailDTO(batchDTO);

                // ✅ Map LabReport
                LabReport lab = batch.getLabReport();
                if (lab != null) {
                    LabReportDTO labDTO = new LabReportDTO();
                    labDTO.setId(lab.getId());
                    labDTO.setLabName(lab.getLabName());
                    labDTO.setManufacturingDate(lab.getManufacturingDate());
                    labDTO.setExpiryDate(lab.getExpiryDate());
                    labDTO.setTestDate(lab.getTestDate());
                    labDTO.setStatus(lab.getStatus());
                    labDTO.setRemarks(lab.getRemarks());
                    labDTO.setFilePath(lab.getFilePath());
                    dto.setLabReportDTO(labDTO);
                }

                // ✅ Map PackagingDetails (all)
                if (batch.getPackagingDetails() != null && !batch.getPackagingDetails().isEmpty()) {
                    List<PackagingDetailDTO> pkgDTOs = batch.getPackagingDetails().stream().map(pkg -> {
                        PackagingDetailDTO pkgDTO = new PackagingDetailDTO();
                        pkgDTO.setId(pkg.getId());
                        pkgDTO.setPacketSize(pkg.getPacketSize());
                        pkgDTO.setUnit(pkg.getUnit());
                        pkgDTO.setPackets(pkg.getPackets());
                        pkgDTO.setRemainingStock(pkg.getRemainingStock());
                        pkgDTO.setBatchId(batch.getId());

                        // ✅ Map DispatchDetails (all)
                        if (pkg.getDispatchDetails() != null && !pkg.getDispatchDetails().isEmpty()) {
                            List<DispatchDetailDTO> dispatchDTOs = pkg.getDispatchDetails().stream().map(dispatch -> {
                                DispatchDetailDTO dispatchDTO = new DispatchDetailDTO();
                                dispatchDTO.setId(dispatch.getId());
                                dispatchDTO.setLotNo(dispatch.getLotNo());
                                dispatchDTO.setNoOfPackets(dispatch.getNoOfPackets());
                                dispatchDTO.setRemarks(dispatch.getRemarks());
                                dispatchDTO.setQrCode(dispatch.getQrCode());
                                dispatchDTO.setPackagingId(pkg.getId());

                                if (dispatch.getCdpo() != null) {
                                    CdpoDTO cdpoDTO = new CdpoDTO();
                                    cdpoDTO.setId(dispatch.getCdpo().getId());
                                    cdpoDTO.setCdpoName(dispatch.getCdpo().getCdpoName());
                                    dispatchDTO.setCdpo(cdpoDTO);
                                }

                                return dispatchDTO;
                            }).collect(Collectors.toList());

                            pkgDTO.setDispatchDetailDTOs(dispatchDTOs); // list of dispatches
                        }

                        return pkgDTO;
                    }).collect(Collectors.toList());

                    dto.setPackagingDetailDTOs(pkgDTOs); // list of packaging details
                }
            }

            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public IngredientDetailDTO getIngredientById(Long id) {
        IngredientDetail ing = ingredientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        IngredientDetailDTO dto = new IngredientDetailDTO();
        dto.setId(ing.getId());
        dto.setType(ing.getType());
        dto.setName(ing.getName());
//        dto.setPrice(ing.getPrice());
        dto.setQuantity(ing.getQuantity());
        dto.setUnit(ing.getUnit());
//        dto.setVendor(ing.getVendor());
//        dto.setTotal(ing.getTotal());
        dto.setBatchNo(ing.getBatchNo());
        dto.setDemandId(ing.getDemand().getId());
        return dto;
    }


//    @Override
//    public List<IngredientDetailDTO> getIngredientsByDemandId(Long demandId) {
//        List<IngredientDetail> ingredients = ingredientRepo.findByDemandId(demandId);
//
//        return ingredients.stream().map(ing -> {
//            IngredientDetailDTO dto = new IngredientDetailDTO();
//            dto.setId(ing.getId());
//            dto.setType(ing.getType());
//            dto.setName(ing.getName());
////            dto.setPrice(ing.getPrice());
//            dto.setQuantity(ing.getQuantity());
//            dto.setUnit(ing.getUnit());

    /// /            dto.setVendor(ing.getVendor());
    /// /            dto.setTotal(ing.getTotal());
//            dto.setBatchNo(ing.getBatchNo());
//            dto.setDemandId(ing.getDemand().getId());
//            return dto;
//        }).collect(Collectors.toList());
//    }
    @Override
    public List<IngredientDetailDTO> getIngredientsByDemandId(Long demandId) {

        // ✅ Get current logged-in user
        String keycloakUserId = keycloakUserService.getCurrentUserId();
        User currentUser = userRepository.findByKeycloakUserId(keycloakUserId)
                .orElseThrow(() -> new IllegalStateException("User not found for keycloak id: " + keycloakUserId));
        String userCdpoName = currentUser.getCdpo().trim();

        return ingredientRepo.findByDemandId(demandId).stream().map(ing -> {
            IngredientDetailDTO dto = new IngredientDetailDTO();
            dto.setId(ing.getId());
            dto.setType(ing.getType());
            dto.setName(ing.getName());
            dto.setQuantity(ing.getQuantity());
            dto.setUnit(ing.getUnit());
            dto.setBatchNo(ing.getBatchNo());
            dto.setDemandId(ing.getDemand().getId());

            // ✅ Map BatchDetail
            BatchDetail batch = ing.getBatchDetail();
            if (batch != null) {
                BatchDetailDTO batchDTO = new BatchDetailDTO();
                batchDTO.setId(batch.getId());
                batchDTO.setQrCode(batch.getQrCode());
                dto.setBatchDetailDTO(batchDTO);

                // ✅ Map LabReport
                LabReport lab = batch.getLabReport();
                if (lab != null) {
                    LabReportDTO labDTO = new LabReportDTO();
                    labDTO.setId(lab.getId());
                    labDTO.setLabName(lab.getLabName());
                    labDTO.setManufacturingDate(lab.getManufacturingDate());
                    labDTO.setExpiryDate(lab.getExpiryDate());
                    labDTO.setTestDate(lab.getTestDate());
                    labDTO.setStatus(lab.getStatus());
                    labDTO.setRemarks(lab.getRemarks());
                    labDTO.setFilePath(lab.getFilePath());
                    dto.setLabReportDTO(labDTO);
                }

                // ✅ Map PackagingDetails (filter dispatches by user CDPO)
                if (batch.getPackagingDetails() != null && !batch.getPackagingDetails().isEmpty()) {
                    List<PackagingDetailDTO> pkgDTOs = batch.getPackagingDetails().stream().map(pkg -> {
                                PackagingDetailDTO pkgDTO = new PackagingDetailDTO();
                                pkgDTO.setId(pkg.getId());
                                pkgDTO.setPacketSize(pkg.getPacketSize());
                                pkgDTO.setUnit(pkg.getUnit());
                                pkgDTO.setPackets(pkg.getPackets());
                                pkgDTO.setRemainingStock(pkg.getRemainingStock());
                                pkgDTO.setBatchId(batch.getId());

                                // ✅ Filter dispatches by current user's CDPO
                                List<DispatchDetailDTO> dispatchDTOs = pkg.getDispatchDetails().stream()
                                        .filter(dispatch -> dispatch.getCdpo() != null &&
                                                dispatch.getCdpo().getCdpoName() != null &&
                                                dispatch.getCdpo().getCdpoName().trim().equalsIgnoreCase(userCdpoName))
                                        .map(dispatch -> {
                                            DispatchDetailDTO dispatchDTO = new DispatchDetailDTO();
                                            dispatchDTO.setId(dispatch.getId());
                                            dispatchDTO.setLotNo(dispatch.getLotNo());
                                            dispatchDTO.setNoOfPackets(dispatch.getNoOfPackets());
                                            dispatchDTO.setRemarks(dispatch.getRemarks());
                                            dispatchDTO.setQrCode(dispatch.getQrCode());
                                            dispatchDTO.setPackagingId(pkg.getId());

                                            // ✅ Map CDPO
                                            CdpoDTO cdpoDTO = new CdpoDTO();
                                            cdpoDTO.setId(dispatch.getCdpo().getId());
                                            cdpoDTO.setCdpoName(dispatch.getCdpo().getCdpoName());

                                            // ✅ Map Sectors for this CDPO
                                            if (dispatch.getCdpo().getSectors() != null && !dispatch.getCdpo().getSectors().isEmpty()) {
                                                List<SectorDTO> sectorDTOs = dispatch.getCdpo().getSectors().stream().map(sector -> {
                                                    SectorDTO sectorDTO = new SectorDTO();
                                                    sectorDTO.setId(sector.getId());
                                                    sectorDTO.setCdpoId(dispatch.getCdpo().getId());
                                                    sectorDTO.setName(sector.getName());
                                                    sectorDTO.setStatus(sector.getStatus());

                                                    // ✅ Map Anganwadi Centers for this sector
                                                    if (sector.getAnganwadiCenters() != null && !sector.getAnganwadiCenters().isEmpty()) {
                                                        List<AnganwadiCenterDTO> awcDTOs = sector.getAnganwadiCenters().stream().map(awc -> {
                                                            AnganwadiCenterDTO awcDTO = new AnganwadiCenterDTO();
                                                            awcDTO.setId(awc.getId());
                                                            awcDTO.setCenterName(awc.getCenterName());
                                                            awcDTO.setCenterAddress(awc.getCenterAddress());
                                                            awcDTO.setStatus(awc.getStatus());
                                                            awcDTO.setSupervisorId(awc.getSupervisor() != null ? awc.getSupervisor().getId() : null);
                                                            awcDTO.setCdpoId(dispatch.getCdpo().getId());
                                                            awcDTO.setDistrictId(dispatch.getCdpo().getDistrict() != null ? dispatch.getCdpo().getDistrict().getId() : null);
                                                            return awcDTO;
                                                        }).collect(Collectors.toList());

                                                        sectorDTO.setAnganwadiCenters(awcDTOs);
                                                    }

                                                    return sectorDTO;
                                                }).collect(Collectors.toList());

                                                cdpoDTO.setSectors(sectorDTOs);
                                            }

                                            dispatchDTO.setCdpo(cdpoDTO);
                                            return dispatchDTO;
                                        })
                                        .collect(Collectors.toList());

                                // Only include packaging if there are dispatches for this CDPO
                                if (!dispatchDTOs.isEmpty()) {
                                    pkgDTO.setDispatchDetailDTOs(dispatchDTOs);
                                    return pkgDTO;
                                } else {
                                    return null; // remove this packaging
                                }
                            })
                            .filter(Objects::nonNull) // remove nulls (packages with no dispatches for current CDPO)
                            .collect(Collectors.toList());

                    dto.setPackagingDetailDTOs(pkgDTOs);
                }

            }

            return dto;
        }).collect(Collectors.toList());
    }


    // ✅ Fix: Ensure this method matches the interface
    @Override
    public LabReportDTO getLabReportByIngredientId(Long ingredientId) {
        BatchDetail batch = batchRepo.findByIngredientId(ingredientId)
                .orElseThrow(() -> new RuntimeException("Batch not found for ingredient"));

        LabReport report = batch.getLabReport();
        if (report == null) {
            throw new RuntimeException("Report not found for this ingredient");
        }

        LabReportDTO dto = new LabReportDTO();
        dto.setId(report.getId());
        dto.setLabName(report.getLabName());
        dto.setManufacturingDate(report.getManufacturingDate());
        dto.setExpiryDate(report.getExpiryDate());
        dto.setTestDate(report.getTestDate());
        dto.setStatus(report.getStatus());
        dto.setRemarks(report.getRemarks());
        dto.setFilePath(report.getFilePath());

        return dto;
    }


    @Override
    public LabReportDTO saveLabReport(LabReportDTO dto) {
        // Create a new LabReport object from the DTO
        LabReport report = new LabReport();
        report.setLabName(dto.getLabName());
        report.setManufacturingDate(dto.getManufacturingDate());
        report.setExpiryDate(dto.getExpiryDate());
        report.setTestDate(dto.getTestDate());
        report.setStatus(dto.getStatus());
        report.setRemarks(dto.getRemarks());
        report.setFilePath(dto.getFilePath());

        // Save the LabReport
        report = labReportRepo.save(report);

        dto.setId(report.getId());

        return dto;
    }
//    @Override
//    public List<LabReportDTO> saveLabReports(List<LabReportDTO> dtos) {
//        List<LabReportDTO> savedReports = new ArrayList<>();
//
//        for (LabReportDTO dto : dtos) {
//            // Create a new LabReport object from the DTO
//            LabReport report = new LabReport();
//            report.setLabName(dto.getLabName());
//            report.setManufacturingDate(dto.getManufacturingDate());
//            report.setExpiryDate(dto.getExpiryDate());
//            report.setTestDate(dto.getTestDate());
//            report.setStatus(dto.getStatus());
//            report.setRemarks(dto.getRemarks());
//            report.setFilePath(dto.getFilePath());
//
//            // Save the LabReport
//            report = labReportRepo.save(report);
//
//            // Find the first BatchDetail where labReport is null (this ensures we get only one matching record)
//            List<BatchDetail> batchDetails = batchRepo.findByLabReportIsNull();
//            if (batchDetails.isEmpty()) {
//                throw new RuntimeException("No batch detail found to link the lab report");
//            }
//
//            // Assuming only one batch detail should be linked to the lab report
//            BatchDetail batchDetail = batchDetails.get(0); // Or you can implement a different selection strategy here
//            batchDetail.setLabReport(report);
//
//            // Save the updated BatchDetail
//            batchRepo.save(batchDetail);
//
//            // Set the ID of the saved LabReport in the DTO to return
//            dto.setId(report.getId());
//
//            // Add the saved DTO to the list of saved reports
//            savedReports.add(dto);
//        }
//
//        // Return the list of saved LabReports
//        return savedReports;
//    }


}
