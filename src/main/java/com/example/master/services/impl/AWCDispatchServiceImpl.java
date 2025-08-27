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
    public AWCDispatch createAWCDispatch(AWCDispatchDTO dto) {
        try {
        PackagingDetail packagingDetail = packagingDetailRepository.findById(dto.getPackagingId())
                .orElseThrow(() -> new RuntimeException("Packaging not found"));

        AWCDispatch awcDispatch = new AWCDispatch();
        awcDispatch.setPackagingDetail(packagingDetail);
        awcDispatch.setAwc(dto.getAwc());
        awcDispatch.setRemark(dto.getRemark());
        awcDispatch.setDispatchPackets(dto.getDispatchPackets());

        return awcDispatchRepository.save(awcDispatch);
        } catch (Exception e) {
            // Log the error if logging is set up
            System.err.println("Error creating AWCDispatch: " + e.getMessage());
            // Optionally rethrow or wrap into a custom exception
            throw new RuntimeException("Failed to create AWC Dispatch: " + e.getMessage(), e);
        }
    }

    @Override
    public List<AWCDispatch> getAllAWCDispatches() {
        return awcDispatchRepository.findAll();
    }
}
