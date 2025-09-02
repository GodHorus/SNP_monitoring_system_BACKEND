package com.example.master.services.impl;

import com.example.master.Dto.BatchDetailDTO;
import com.example.master.Dto.IngredientDetailDTO;
import com.example.master.Dto.LabReportDTO;
import com.example.master.Dto.PackagingDetailDTO;
import com.example.master.model.BatchDetail;
import com.example.master.model.PackagingDetail;
import com.example.master.repository.BatchDetailRepository;
import com.example.master.repository.PackagingDetailRepository;
import com.example.master.services.PackagingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PackagingServiceImpl implements PackagingService {

    private final PackagingDetailRepository packagingRepo;
    private final BatchDetailRepository batchRepo;

    public PackagingServiceImpl(PackagingDetailRepository packagingRepo, BatchDetailRepository batchRepo) {
        this.packagingRepo = packagingRepo;
        this.batchRepo = batchRepo;
    }

//    @Override
//    public PackagingDetailDTO savePackaging(PackagingDetailDTO dto) {
//        BatchDetail batch = batchRepo.findById(dto.getBatchId())
//                .orElseThrow(() -> new RuntimeException("Batch not found"));
//
//        PackagingDetail packaging = new PackagingDetail();
//        packaging.setAvailableStock(dto.getAvailableStock());
//        packaging.setPacketSize(dto.getPacketSize());
//        packaging.setUnit(dto.getUnit());
//        packaging.setPackets(dto.getPackets());
//        packaging.setRemainingStock(dto.getRemainingStock());
//        packaging.setBatchDetail(batch);
//
//        packaging = packagingRepo.save(packaging);
//        dto.setId(packaging.getId());
//        return dto;
//    }

    @Override
    public List<PackagingDetailDTO> savePackaging(List<PackagingDetailDTO> dtoList) {
        List<PackagingDetail> packagingEntities = new ArrayList<>();

        for (PackagingDetailDTO dto : dtoList) {
            BatchDetail batch = batchRepo.findById(dto.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch not found: " + dto.getBatchId()));

            PackagingDetail packaging = new PackagingDetail();
            packaging.setPacketSize(dto.getPacketSize());
            packaging.setUnit(dto.getUnit());
            packaging.setPackets(dto.getPackets());
            packaging.setRemainingStock(dto.getRemainingStock());
            packaging.setBatchDetail(batch);

            packagingEntities.add(packaging);
        }

        // Save all at once
        List<PackagingDetail> saved = packagingRepo.saveAll(packagingEntities);

        // Map back to DTO
        return saved.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PackagingDetailDTO> getAllPackaging() {
        return packagingRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PackagingDetailDTO mapToDTO(PackagingDetail packaging) {
        PackagingDetailDTO dto = new PackagingDetailDTO();
        dto.setId(packaging.getId());
        dto.setPacketSize(packaging.getPacketSize());
        dto.setUnit(packaging.getUnit());
        dto.setPackets(packaging.getPackets());
        dto.setRemainingStock(packaging.getRemainingStock());
        dto.setBatchId(packaging.getBatchDetail().getId());

        // Map nested BatchDetail
        BatchDetail batch = packaging.getBatchDetail();
        BatchDetailDTO batchDTO = new BatchDetailDTO();
        batchDTO.setId(batch.getId());
        batchDTO.setIngredientId(batch.getIngredient().getId());
        batchDTO.setQrCode(batch.getQrCode());
        batchDTO.setLabReportId(batch.getLabReport().getId());

        // Map ingredient
        IngredientDetailDTO ingredientDTO = new IngredientDetailDTO();
        ingredientDTO.setId(batch.getIngredient().getId());
        ingredientDTO.setName(batch.getIngredient().getName());
        ingredientDTO.setType(batch.getIngredient().getType());
        ingredientDTO.setPrice(batch.getIngredient().getPrice());
        ingredientDTO.setQuantity(batch.getIngredient().getQuantity());
        ingredientDTO.setUnit(batch.getIngredient().getUnit());
        ingredientDTO.setVendor(batch.getIngredient().getVendor());
        ingredientDTO.setTotal(batch.getIngredient().getTotal());

        // Map lab report
        LabReportDTO labDTO = new LabReportDTO();
        labDTO.setId(batch.getLabReport().getId());
        labDTO.setLabName(batch.getLabReport().getLabName());
        labDTO.setManufacturingDate(batch.getLabReport().getManufacturingDate());
        labDTO.setExpiryDate(batch.getLabReport().getExpiryDate());
        labDTO.setTestDate(batch.getLabReport().getTestDate());
        labDTO.setStatus(batch.getLabReport().getStatus());
        labDTO.setRemarks(batch.getLabReport().getRemarks());
        labDTO.setFilePath(batch.getLabReport().getFilePath());

        batchDTO.setIngredient(ingredientDTO);
        batchDTO.setLabReport(labDTO);

        dto.setBatchDetailDTO(batchDTO);
        return dto;
    }
}
