package com.example.master.services.impl;

import com.example.master.Dto.BatchDetailDTO;
import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import com.example.master.model.BatchDetail;
import com.example.master.model.IngredientDetail;
import com.example.master.model.LabReport;
import com.example.master.repository.BatchDetailRepository;
import com.example.master.repository.IngredientDetailRepository;
import com.example.master.repository.LabReportRepository;
import com.example.master.services.BatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BatchServiceImpl implements BatchService {

    private final BatchDetailRepository batchRepo;
    private final IngredientDetailRepository ingredientRepo;
    private final LabReportRepository labReportRepo;

    public BatchServiceImpl(BatchDetailRepository batchRepo,
                            IngredientDetailRepository ingredientRepo,
                            LabReportRepository labReportRepo) {
        this.batchRepo = batchRepo;
        this.ingredientRepo = ingredientRepo;
        this.labReportRepo = labReportRepo;
    }

    @Override
    @Transactional
    public List<BatchDetailDTO> saveBatches(List<BatchDetailDTO> dtos) {
        List<BatchDetail> saved = dtos.stream().map(dto -> {
            // check if batch already exists for ingredient
            BatchDetail batch = batchRepo.findByIngredientId(dto.getIngredientId())
                    .orElse(new BatchDetail());

            IngredientDetail ingredient = ingredientRepo.findById(dto.getIngredientId())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));
            batch.setIngredient(ingredient);

            batch.setQrCode(dto.getQrCode());

            if (dto.getLabReportId() != null) {
                LabReport report = labReportRepo.findById(dto.getLabReportId())
                        .orElseThrow(() -> new RuntimeException("Lab Report not found"));
                batch.setLabReport(report);
                report.setBatchDetail(batch);
            }

            return batchRepo.save(batch);
        }).collect(Collectors.toList());

        return saved.stream().map(this::toDTO).collect(Collectors.toList());
    }


    @Override
    public List<BatchDetailDTO> getAllBatches() {
        return batchRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private BatchDetailDTO toDTO(BatchDetail batch) {
        BatchDetailDTO dto = new BatchDetailDTO();
        dto.setId(batch.getId());
        dto.setQrCode(batch.getQrCode());
//        dto.setLabReportId(batch.getLabReport().getId());

        // Ensure ingredient and lab report are not null before accessing their fields
        if (batch.getIngredient() != null) {
            dto.setIngredientId(batch.getIngredient().getId());
            IngredientDetailDTO ingDto = new IngredientDetailDTO();
            ingDto.setId(batch.getIngredient().getId());
            ingDto.setName(batch.getIngredient().getName());
            ingDto.setType(batch.getIngredient().getType());
            ingDto.setPrice(batch.getIngredient().getPrice());
            ingDto.setDemandId(batch.getIngredient().getDemand().getId());
            ingDto.setBatchNo(batch.getIngredient().getBatchNo());
            ingDto.setQuantity(batch.getIngredient().getQuantity());
            ingDto.setUnit(batch.getIngredient().getUnit());
            ingDto.setVendor(batch.getIngredient().getVendor());
            ingDto.setTotal(batch.getIngredient().getTotal());
            dto.setIngredient(ingDto);
        }

        // Nested lab report
        if (batch.getLabReport() != null) {
            dto.setLabReportId(batch.getLabReport().getId());
            LabReportDTO labDto = new LabReportDTO();
            labDto.setId(batch.getLabReport().getId());
            labDto.setLabName(batch.getLabReport().getLabName());
            labDto.setStatus(batch.getLabReport().getStatus());
            labDto.setRemarks(batch.getLabReport().getRemarks());
            dto.setLabReport(labDto);
        }

        return dto;
    }

}
