package com.example.master.services.impl;

import com.example.master.Dto.*;
import com.example.master.model.*;
import com.example.master.repository.BatchDetailRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.repository.IngredientDetailRepository;
import com.example.master.repository.LabReportRepository;
import com.example.master.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientDetailRepository ingredientRepo;
    private final BatchDetailRepository batchRepo;
    private final LabReportRepository labReportRepo;
    private final DemandRepository demandRepo;

    public IngredientServiceImpl(IngredientDetailRepository ingredientRepo,
                                 BatchDetailRepository batchRepo,
                                 LabReportRepository labReportRepo,
                                 DemandRepository demandRepo) {
        this.ingredientRepo = ingredientRepo;
        this.batchRepo = batchRepo;
        this.labReportRepo = labReportRepo;
        this.demandRepo = demandRepo;
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

        // Save Ingredient
        IngredientDetail ingredient = new IngredientDetail();
        ingredient.setType(dto.getType());
        ingredient.setName(dto.getName());
        ingredient.setPrice(dto.getPrice());
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setUnit(dto.getUnit());
        ingredient.setVendor(dto.getVendor());
        ingredient.setTotal(dto.getTotal());

        // Auto-generate batchNo
        String batchNo = generateNextBatchNo();
        ingredient.setBatchNo(batchNo);
        ingredient.setDemand(demand);
        ingredient = ingredientRepo.save(ingredient);

        // Create BatchDetail linked to Ingredient
        BatchDetail batch = new BatchDetail();
        batch.setIngredient(ingredient); // ✅ FIXED setter
        batch.setQrCode("QR-" + ingredient.getId()); // placeholder QR
        batchRepo.save(batch);

        dto.setId(ingredient.getId());
        dto.setBatchNo(batchNo);
        return dto;
    }

    @Override
    public List<IngredientDetailDTO> saveIngredients(List<IngredientDetailDTO> dtos) {
        return dtos.stream().map(this::saveIngredient).collect(Collectors.toList());
    }

//    @Override
//    public List<IngredientDetailDTO> getAllIngredients() {
//        return ingredientRepo.findAll().stream().map(ing -> {
//            IngredientDetailDTO dto = new IngredientDetailDTO();
//            dto.setId(ing.getId());
//            dto.setType(ing.getType());
//            dto.setName(ing.getName());
//            dto.setPrice(ing.getPrice());
//            dto.setQuantity(ing.getQuantity());
//            dto.setUnit(ing.getUnit());
//            dto.setVendor(ing.getVendor());
//            dto.setTotal(ing.getTotal());
//            dto.setBatchNo(ing.getBatchNo());
//            dto.setDemandId(ing.getDemand().getId());
//            return dto;
//        }).collect(Collectors.toList());
//    }

    @Override
    public List<IngredientDetailDTO> getAllIngredients() {
        return ingredientRepo.findAll().stream().map(ing -> {
            IngredientDetailDTO dto = new IngredientDetailDTO();
            dto.setId(ing.getId());
            dto.setType(ing.getType());
            dto.setName(ing.getName());
            dto.setPrice(ing.getPrice());
            dto.setQuantity(ing.getQuantity());
            dto.setUnit(ing.getUnit());
            dto.setVendor(ing.getVendor());
            dto.setTotal(ing.getTotal());
            dto.setBatchNo(ing.getBatchNo());
            dto.setDemandId(ing.getDemand().getId());

            // ✅ Map BatchDetail
            BatchDetail batch = ing.getBatchDetail();
            if (batch != null) {
                BatchDetailDTO batchDTO = new BatchDetailDTO();
                batchDTO.setId(batch.getId());
                batchDTO.setIngredientId(ing.getId());
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

                // ✅ Map PackagingDetail (first one if multiple)
                if (batch.getPackagingDetails() != null && !batch.getPackagingDetails().isEmpty()) {
                    PackagingDetail pkg = batch.getPackagingDetails().get(0);
                    PackagingDetailDTO pkgDTO = new PackagingDetailDTO();
                    pkgDTO.setId(pkg.getId());
                    pkgDTO.setPacketSize(pkg.getPacketSize());
                    pkgDTO.setUnit(pkg.getUnit());
                    pkgDTO.setPackets(pkg.getPackets());
                    pkgDTO.setRemainingStock(pkg.getRemainingStock());
                    pkgDTO.setBatchId(batch.getId());
                    dto.setPackagingDetailDTO(pkgDTO);

                    // ✅ Map DispatchDetail
                    DispatchDetail dispatch = pkg.getDispatchDetail();
                    if (dispatch != null) {
                        DispatchDetailDTO dispatchDTO = new DispatchDetailDTO();
                        dispatchDTO.setId(dispatch.getId());
                        dispatchDTO.setLotNo(dispatch.getLotNo());
                        dispatchDTO.setNoOfPackets(dispatch.getNoOfPackets());
                        dispatchDTO.setRemarks(dispatch.getRemarks());
                        dispatchDTO.setQrCode(dispatch.getLotNo()); // or actual QR if available
                        dispatchDTO.setPackagingId(pkg.getId());

                        if (dispatch.getCdpo() != null) {
                            CdpoDTO cdpoDTO = new CdpoDTO();
                            cdpoDTO.setId(dispatch.getCdpo().getId());
                            cdpoDTO.setCdpoName(dispatch.getCdpo().getCdpoName());
                            dispatchDTO.setCdpo(cdpoDTO);
                        }

                        dto.setDispatchDetailDTO(dispatchDTO);
                    }
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
        dto.setPrice(ing.getPrice());
        dto.setQuantity(ing.getQuantity());
        dto.setUnit(ing.getUnit());
        dto.setVendor(ing.getVendor());
        dto.setTotal(ing.getTotal());
        dto.setBatchNo(ing.getBatchNo());
        dto.setDemandId(ing.getDemand().getId());
        return dto;
    }


    @Override
    public List<IngredientDetailDTO> getIngredientsByDemandId(Long demandId) {
        List<IngredientDetail> ingredients = ingredientRepo.findByDemandId(demandId);

        return ingredients.stream().map(ing -> {
            IngredientDetailDTO dto = new IngredientDetailDTO();
            dto.setId(ing.getId());
            dto.setType(ing.getType());
            dto.setName(ing.getName());
            dto.setPrice(ing.getPrice());
            dto.setQuantity(ing.getQuantity());
            dto.setUnit(ing.getUnit());
            dto.setVendor(ing.getVendor());
            dto.setTotal(ing.getTotal());
            dto.setBatchNo(ing.getBatchNo());
            dto.setDemandId(ing.getDemand().getId());
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
//        dto.setBatchId(batch.getId());
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

        // Find the first BatchDetail where labReport is null (this ensures we get only one matching record)

//    / /        Optional<BatchDetail> batchDetails = batchRepo.findByLabReportIsNull();
//    / /        if (batchDetails.isEmpty()) {
//    / /            throw new RuntimeException("No batch detail found to link the lab report");
//    / /        }
//    / /
//    / /        // Assuming only one batch detail should be linked to the lab report
//    / /        BatchDetail batchDetail = batchDetails.get(); // Or you can implement a different selection strategy here
//    / /        batchDetail.setLabReport(report);
//    / /
//    / /        // Save the updated BatchDetail
//    / /        batchRepo.save(batchDetail);
//    / /
//    / /        // Set the ID of the saved LabReport in the DTO to return
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
