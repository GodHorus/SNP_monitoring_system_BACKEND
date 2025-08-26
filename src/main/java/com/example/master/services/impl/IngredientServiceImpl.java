package com.example.master.services.impl;

import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import com.example.master.model.Demand;
import com.example.master.model.IngredientDetail;
import com.example.master.model.LabReport;
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
    private final LabReportRepository labReportRepo;
    private final DemandRepository demandRepo;

    public IngredientServiceImpl(IngredientDetailRepository ingredientRepo,
                                 LabReportRepository labReportRepo,
                                 DemandRepository demandRepo) {
        this.ingredientRepo = ingredientRepo;
        this.labReportRepo = labReportRepo;
        this.demandRepo = demandRepo;
    }

    @Override
    public IngredientDetailDTO saveIngredient(IngredientDetailDTO dto) {
        Demand demand = demandRepo.findById(dto.getDemandId())
                .orElseThrow(() -> new RuntimeException("Demand not found"));

        IngredientDetail ingredient = new IngredientDetail();
        ingredient.setType(dto.getType());
        ingredient.setName(dto.getName());
        ingredient.setPrice(dto.getPrice());
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setUnit(dto.getUnit());
        ingredient.setVendor(dto.getVendor());
        ingredient.setTotal(dto.getTotal());
        ingredient.setBatchNo(dto.getBatchNo());
        ingredient.setDemand(demand);

        ingredient = ingredientRepo.save(ingredient);
        dto.setId(ingredient.getId());
        return dto;
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

    @Override
    public LabReportDTO saveLabReport(LabReportDTO dto) {
        IngredientDetail ingredient = ingredientRepo.findById(dto.getIngredientId())
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        LabReport report = new LabReport();
        report.setLabName(dto.getLabName());
        report.setManufacturingDate(dto.getManufacturingDate());
        report.setExpiryDate(dto.getExpiryDate());
        report.setTestDate(dto.getTestDate());
        report.setStatus(dto.getStatus());
        report.setRemarks(dto.getRemarks());
        report.setFilePath(dto.getFilePath());
        report.setIngredientDetail(ingredient);

        report = labReportRepo.save(report);
        dto.setId(report.getId());
        return dto;
    }

    @Override
    public LabReportDTO getLabReportByIngredientId(Long ingredientId) {
        LabReport report = labReportRepo.findAll()
                .stream()
                .filter(r -> r.getIngredientDetail().getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Report not found"));

        LabReportDTO dto = new LabReportDTO();
        dto.setId(report.getId());
        dto.setLabName(report.getLabName());
        dto.setManufacturingDate(report.getManufacturingDate());
        dto.setExpiryDate(report.getExpiryDate());
        dto.setTestDate(report.getTestDate());
        dto.setStatus(report.getStatus());
        dto.setRemarks(report.getRemarks());
        dto.setFilePath(report.getFilePath());
        dto.setIngredientId(report.getIngredientDetail().getId());
        return dto;
    }
}
