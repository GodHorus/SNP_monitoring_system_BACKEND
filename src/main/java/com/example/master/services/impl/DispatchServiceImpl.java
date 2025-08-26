package com.example.master.services.impl;

import com.example.master.Dto.DispatchDetailDTO;
import com.example.master.model.DispatchDetail;
import com.example.master.model.PackagingDetail;
import com.example.master.repository.DispatchDetailRepository;
import com.example.master.repository.PackagingDetailRepository;
import com.example.master.services.DispatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DispatchServiceImpl implements DispatchService {

    private final DispatchDetailRepository dispatchRepo;
    private final PackagingDetailRepository packagingRepo;

    public DispatchServiceImpl(DispatchDetailRepository dispatchRepo, PackagingDetailRepository packagingRepo) {
        this.dispatchRepo = dispatchRepo;
        this.packagingRepo = packagingRepo;
    }

    @Override
    public DispatchDetailDTO saveDispatch(DispatchDetailDTO dto) {
        PackagingDetail packaging = packagingRepo.findById(dto.getPackagingId())
                .orElseThrow(() -> new RuntimeException("Packaging not found"));

        DispatchDetail dispatch = new DispatchDetail();
        dispatch.setLotNo(dto.getLotNo());
        dispatch.setCdpo(dto.getCdpo());
        dispatch.setNoOfPackets(dto.getNoOfPackets());
        dispatch.setRemarks(dto.getRemarks());
        dispatch.setQrCode(dto.getQrCode());
        dispatch.setPackagingDetail(packaging);

        dispatch = dispatchRepo.save(dispatch);
        dto.setId(dispatch.getId());
        return dto;
    }

    @Override
    public List<DispatchDetailDTO> getAllDispatches() {
        return dispatchRepo.findAll().stream().map(d -> {
            DispatchDetailDTO dto = new DispatchDetailDTO();
            dto.setId(d.getId());
            dto.setLotNo(d.getLotNo());
            dto.setCdpo(d.getCdpo());
            dto.setNoOfPackets(d.getNoOfPackets());
            dto.setRemarks(d.getRemarks());
            dto.setQrCode(d.getQrCode());
            dto.setPackagingId(d.getPackagingDetail().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
