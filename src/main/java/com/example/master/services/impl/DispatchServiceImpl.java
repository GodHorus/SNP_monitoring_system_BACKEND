package com.example.master.services.impl;

import com.example.master.Dto.DispatchDetailDTO;
import com.example.master.model.Cdpo;
import com.example.master.model.DispatchDetail;
import com.example.master.model.PackagingDetail;
import com.example.master.repository.CdpoRepository;
import com.example.master.repository.DispatchDetailRepository;
import com.example.master.repository.PackagingDetailRepository;
import com.example.master.services.DispatchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DispatchServiceImpl implements DispatchService {

    private final DispatchDetailRepository dispatchRepo;
    private final PackagingDetailRepository packagingRepo;
    private final CdpoRepository cdpoRepo;

    private static final AtomicInteger lotCounter = new AtomicInteger(1);

    public DispatchServiceImpl(DispatchDetailRepository dispatchRepo,
                               PackagingDetailRepository packagingRepo,
                               CdpoRepository cdpoRepo) {
        this.dispatchRepo = dispatchRepo;
        this.packagingRepo = packagingRepo;
        this.cdpoRepo = cdpoRepo;
    }

    @Override
    public List<DispatchDetailDTO> saveDispatchDetails(List<DispatchDetailDTO> dtoList) {
        List<DispatchDetail> entities = new ArrayList<>();

        for (DispatchDetailDTO dto : dtoList) {
            PackagingDetail packaging = packagingRepo.findById(dto.getPackagingId())
                    .orElseThrow(() -> new RuntimeException("Packaging not found: " + dto.getPackagingId()));

            Cdpo cdpo = cdpoRepo.findById(dto.getCdpoId())
                    .orElseThrow(() -> new RuntimeException("CDPO not found: " + dto.getCdpoId()));

            DispatchDetail dispatch = new DispatchDetail();
            dispatch.setLotNo("LOT-" + String.format("%04d", lotCounter.getAndIncrement()));
            dispatch.setNoOfPackets(dto.getNoOfPackets());
            dispatch.setRemarks(dto.getRemarks());
            dispatch.setPackagingDetail(packaging);
            dispatch.setCdpo(cdpo);

            entities.add(dispatch);
        }

        List<DispatchDetail> saved = dispatchRepo.saveAll(entities);

        // Map back to DTOs
        List<DispatchDetailDTO> result = new ArrayList<>();
        for (DispatchDetail d : saved) {
            DispatchDetailDTO dto = new DispatchDetailDTO();
            dto.setId(d.getId());
            dto.setLotNo(d.getLotNo());
            dto.setNoOfPackets(d.getNoOfPackets());
            dto.setRemarks(d.getRemarks());

            dto.setPackagingId(d.getPackagingDetail().getId());
            dto.setCdpoId(d.getCdpo().getId());

            // Optional: map nested DTOs using mapper
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<DispatchDetailDTO> getAllDispatchDetails() {
        List<DispatchDetail> entities = dispatchRepo.findAll();
        List<DispatchDetailDTO> result = new ArrayList<>();

        for (DispatchDetail d : entities) {
            DispatchDetailDTO dto = new DispatchDetailDTO();
            dto.setId(d.getId());
            dto.setLotNo(d.getLotNo());
            dto.setNoOfPackets(d.getNoOfPackets());
            dto.setRemarks(d.getRemarks());
            dto.setPackagingId(d.getPackagingDetail().getId());
            dto.setCdpoId(d.getCdpo().getId());
            result.add(dto);
        }

        return result;
    }
}
