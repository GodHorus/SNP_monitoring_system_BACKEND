package com.example.master.services.impl;

import com.example.master.Dto.AWCDispatchDTO;
import com.example.master.model.AWCDispatch;
import com.example.master.model.PackagingDetail;
import com.example.master.repository.AWCDispatchRepository;
import com.example.master.repository.PackagingDetailRepository;
import com.example.master.services.AWCDispatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AWCDispatchServiceImpl implements AWCDispatchService {

    private final AWCDispatchRepository awcDispatchRepository;
    private final PackagingDetailRepository packagingDetailRepository;

    public AWCDispatchServiceImpl(AWCDispatchRepository awcDispatchRepository,
                                  PackagingDetailRepository packagingDetailRepository) {
        this.awcDispatchRepository = awcDispatchRepository;
        this.packagingDetailRepository = packagingDetailRepository;
    }

    @Override
    public AWCDispatchDTO createAWCDispatch(AWCDispatchDTO dto) {
        PackagingDetail packagingDetail = packagingDetailRepository.findById(dto.getPackagingId())
                .orElseThrow(() -> new RuntimeException("Packaging not found"));

        AWCDispatch awcDispatch = new AWCDispatch();
        awcDispatch.setPackagingDetail(packagingDetail);
        awcDispatch.setAwc(dto.getAwc());
        awcDispatch.setRemark(dto.getRemark());
        awcDispatch.setDispatchPackets(dto.getDispatchPackets());

        AWCDispatch saved = awcDispatchRepository.save(awcDispatch);
        return convertToDTO(saved);
    }

    @Override
    public List<AWCDispatchDTO> getAllAWCDispatches() {
        return awcDispatchRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // ---------- PRIVATE MAPPER ----------
    private AWCDispatchDTO convertToDTO(AWCDispatch dispatch) {
        AWCDispatchDTO dto = new AWCDispatchDTO();
        dto.setPackagingId(dispatch.getPackagingDetail().getId()); // only ID
        dto.setAwc(dispatch.getAwc());
        dto.setRemark(dispatch.getRemark());
        dto.setDispatchPackets(dispatch.getDispatchPackets());
        return dto;
    }
}
