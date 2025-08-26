package com.example.master.services.impl;

import com.example.master.Dto.PackagingDetailDTO;
import com.example.master.model.BatchDetail;
import com.example.master.model.PackagingDetail;
import com.example.master.repository.BatchDetailRepository;
import com.example.master.repository.PackagingDetailRepository;
import com.example.master.services.PackagingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public PackagingDetailDTO savePackaging(PackagingDetailDTO dto) {
        BatchDetail batch = batchRepo.findById(dto.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        PackagingDetail packaging = new PackagingDetail();
        packaging.setAvailableStock(dto.getAvailableStock());
        packaging.setPacketSize(dto.getPacketSize());
        packaging.setUnit(dto.getUnit());
        packaging.setPackets(dto.getPackets());
        packaging.setRemainingStock(dto.getRemainingStock());
        packaging.setBatchDetail(batch);

        packaging = packagingRepo.save(packaging);
        dto.setId(packaging.getId());
        return dto;
    }

    @Override
    public List<PackagingDetailDTO> getAllPackaging() {
        return packagingRepo.findAll().stream().map(p -> {
            PackagingDetailDTO dto = new PackagingDetailDTO();
            dto.setId(p.getId());
            dto.setAvailableStock(p.getAvailableStock());
            dto.setPacketSize(p.getPacketSize());
            dto.setUnit(p.getUnit());
            dto.setPackets(p.getPackets());
            dto.setRemainingStock(p.getRemainingStock());
            dto.setBatchId(p.getBatchDetail().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
