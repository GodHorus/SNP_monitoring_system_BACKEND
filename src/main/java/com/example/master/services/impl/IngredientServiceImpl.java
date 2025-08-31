package com.example.master.services.impl;

import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import com.example.master.model.BatchDetail;
import com.example.master.model.Demand;
import com.example.master.model.IngredientDetail;
import com.example.master.model.LabReport;
import com.example.master.repository.BatchDetailRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.repository.IngredientDetailRepository;
import com.example.master.repository.LabReportRepository;
import com.example.master.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        dto.setBatchId(batch.getId());
        return dto;
    }

    @Override
    public LabReportDTO saveLabReport(LabReportDTO dto) {
        BatchDetail batch = batchRepo.findById(dto.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        LabReport report = new LabReport();
        report.setLabName(dto.getLabName());
        report.setManufacturingDate(dto.getManufacturingDate());
        report.setExpiryDate(dto.getExpiryDate());
        report.setTestDate(dto.getTestDate());
        report.setStatus(dto.getStatus());
        report.setRemarks(dto.getRemarks());
        report.setFilePath(dto.getFilePath());

        report = labReportRepo.save(report);
        batch.setLabReport(report);
        batchRepo.save(batch);

        dto.setId(report.getId());
        return dto;
    }
}
