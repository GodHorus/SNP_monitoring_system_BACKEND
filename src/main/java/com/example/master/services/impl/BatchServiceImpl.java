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

import java.util.ArrayList;
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

//    @Override
//    @Transactional
//    public List<BatchDetailDTO> saveBatches(List<BatchDetailDTO> dtos) {
//        List<BatchDetail> saved = dtos.stream().map(dto -> {
//            BatchDetail batch = dto.getId() != null ?
//                    batchRepo.findById(dto.getId()).orElse(new BatchDetail()) :
//                    new BatchDetail();
//
//            batch.setQrCode(dto.getQrCode());
//
//            if (dto.getLabReportId() != null) {
//                LabReport report = labReportRepo.findById(dto.getLabReportId())
//                        .orElseThrow(() -> new RuntimeException("Lab Report not found"));
//                batch.setLabReport(report);
//                report.setBatchDetail(batch);
//            }
//
//            // ✅ save multiple ingredients
//            if (dto.getIngredients() != null) {
//                List<IngredientDetail> ingredients = dto.getIngredients().stream().map(ingDto -> {
//                    IngredientDetail ing = ingDto.getId() != null ?
//                            ingredientRepo.findById(ingDto.getId()).orElse(new IngredientDetail()) :
//                            new IngredientDetail();
//                    ing.setName(ingDto.getName());
//                    ing.setType(ingDto.getType());
//                    ing.setQuantity(ingDto.getQuantity());
//                    ing.setUnit(ingDto.getUnit());
//                    ing.setBatchDetail(batch);
//                    return ing;
//                }).collect(Collectors.toList());
//
//                batch.setIngredients(ingredients);
//            }
//
//            return batchRepo.save(batch);
//        }).collect(Collectors.toList());
//
//        return saved.stream().map(this::toDTO).collect(Collectors.toList());
//    }

    @Override
    @Transactional
    public List<BatchDetailDTO> saveBatches(List<BatchDetailDTO> dtos) {
        List<BatchDetail> saved = dtos.stream().map(dto -> {
            BatchDetail batch;

            // ✅ If ID is provided, fetch existing batch
            if (dto.getId() != null) {
                batch = batchRepo.findById(dto.getId())
                        .orElseThrow(() -> new RuntimeException("Batch not found"));
            }
            // ✅ If batchNo is provided, fetch existing batch
            else if (dto.getBatchNo() != null) {
                batch = batchRepo.findByIngredients_BatchNo(dto.getBatchNo())
                        .orElse(new BatchDetail());
            }
            // ✅ Otherwise create a new batch
            else {
                batch = new BatchDetail();
            }

            // ✅ Always update QR if passed
            if (dto.getQrCode() != null) {
                batch.setQrCode(dto.getQrCode());
            }

            if  (dto.getTotalQuantity() != null) {
                batch.setTotalQuantity(dto.getTotalQuantity());
            }

            // ✅ Set LabReport if provided
            if (dto.getLabReportId() != null) {
                LabReport report = labReportRepo.findById(dto.getLabReportId())
                        .orElseThrow(() -> new RuntimeException("Lab Report not found"));
                batch.setLabReport(report);
                report.setBatchDetail(batch);
            }

            // ✅ Save or attach multiple ingredients
            if (dto.getIngredients() != null) {
                List<IngredientDetail> ingredients = dto.getIngredients().stream().map(ingDto -> {
                    IngredientDetail ing = ingDto.getId() != null ?
                            ingredientRepo.findById(ingDto.getId()).orElse(new IngredientDetail()) :
                            new IngredientDetail();
                    ing.setName(ingDto.getName());
                    ing.setType(ingDto.getType());
                    ing.setQuantity(ingDto.getQuantity());
                    ing.setUnit(ingDto.getUnit());
                    ing.setBatchDetail(batch);
                    ing.setBatchNo(ingDto.getBatchNo() != null ? ingDto.getBatchNo() : batch.getIngredients().isEmpty() ? null : batch.getIngredients().get(0).getBatchNo());
                    return ing;
                }).collect(Collectors.toList());

                // merge with existing ingredients
                if (batch.getIngredients() == null) {
                    batch.setIngredients(new ArrayList<>());
                }
                batch.getIngredients().addAll(ingredients);
            }
//            if (dto.getIngredientIds() != null) {
//                List<IngredientDetail> ingredients = dto.getIngredientIds().stream()
//                        .map(id -> ingredientRepo.findById(id)
//                                .orElseThrow(() -> new RuntimeException("Ingredient not found: " + id)))
//                        .collect(Collectors.toList());
//
//                // Attach ingredients to batch
//                ingredients.forEach(ing -> {
//                    ing.setBatchDetail(batch);
//                    ing.setBatchNo(batch.getBatchNo());
//                });
//
//                batch.setIngredients(ingredients);
//            }


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
        dto.setTotalQuantity(batch.getTotalQuantity());

        // ✅ map ingredients
        if (batch.getIngredients() != null) {
            List<IngredientDetailDTO> ingDtos = batch.getIngredients().stream().map(ing -> {
                IngredientDetailDTO ingDto = new IngredientDetailDTO();
                ingDto.setId(ing.getId());
                ingDto.setName(ing.getName());
                ingDto.setType(ing.getType());
                ingDto.setQuantity(ing.getQuantity());
                ingDto.setUnit(ing.getUnit());
                ingDto.setBatchId(batch.getId());
                ingDto.setBatchNo(ing.getBatchNo());
                ingDto.setDemandId(ing.getDemand() != null ? ing.getDemand().getId() : null);
                return ingDto;
            }).collect(Collectors.toList());
            dto.setIngredients(ingDtos);
        }

        // ✅ map lab report
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
