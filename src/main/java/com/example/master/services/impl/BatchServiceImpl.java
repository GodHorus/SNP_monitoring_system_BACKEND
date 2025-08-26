package com.example.master.services.impl;

import com.example.master.Dto.BatchDetailDTO;
import com.example.master.model.BatchDetail;
import com.example.master.model.LabReport;
import com.example.master.repository.BatchDetailRepository;
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
    private final LabReportRepository labReportRepo;

    public BatchServiceImpl(BatchDetailRepository batchRepo, LabReportRepository labReportRepo) {
        this.batchRepo = batchRepo;
        this.labReportRepo = labReportRepo;
    }

    @Override
    public BatchDetailDTO saveBatch(BatchDetailDTO dto) {
        BatchDetail batch = new BatchDetail();
        batch.setType(dto.getType());
        batch.setBatchNo(dto.getBatchNo());
        batch.setQuantity(dto.getQuantity());
        batch.setQrCode(dto.getQrCode());

        if (dto.getLabReportId() != null) {
            LabReport report = labReportRepo.findById(dto.getLabReportId())
                    .orElseThrow(() -> new RuntimeException("Lab Report not found"));
            batch.setLabReport(report);
        }

        batch = batchRepo.save(batch);
        dto.setId(batch.getId());
        return dto;
    }

    @Override
    public List<BatchDetailDTO> getAllBatches() {
        return batchRepo.findAll().stream().map(b -> {
            BatchDetailDTO dto = new BatchDetailDTO();
            dto.setId(b.getId());
            dto.setType(b.getType());
            dto.setBatchNo(b.getBatchNo());
            dto.setQuantity(b.getQuantity());
            dto.setQrCode(b.getQrCode());
            if (b.getLabReport() != null) {
                dto.setLabReportId(b.getLabReport().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
