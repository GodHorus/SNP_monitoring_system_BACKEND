package com.example.master.services.impl;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.model.DispatchDetail;
import com.example.master.repository.AcceptDemandRepository;
import com.example.master.repository.DispatchDetailRepository;
import com.example.master.services.AcceptDemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptDemandServiceImpl implements AcceptDemandService {

    private final AcceptDemandRepository acceptDemandRepository;
    private final DispatchDetailRepository dispatchDetailRepository;

    public AcceptDemandServiceImpl(AcceptDemandRepository acceptDemandRepository,
                                   DispatchDetailRepository dispatchDetailRepository) {
        this.acceptDemandRepository = acceptDemandRepository;
        this.dispatchDetailRepository = dispatchDetailRepository;
    }

//    @Override
//    public AcceptDemand createAcceptDemand(AcceptDemandDTO dto) {
//        DispatchDetail dispatchDetail = dispatchDetailRepository.findById(dto.getDispatchId())
//                .orElseThrow(() -> new RuntimeException("Dispatch not found"));
//
//        AcceptDemand acceptDemand = new AcceptDemand();
//        acceptDemand.setDispatchDetail(dispatchDetail);
//        acceptDemand.setReceivedPackets(dto.getReceivedPackets());
//        acceptDemand.setRemarks(dto.getRemarks());
//
//        return acceptDemandRepository.save(acceptDemand);
//    }

    @Override
    public List<AcceptDemand> createAcceptDemands(List<AcceptDemandDTO> dtos) {
        List<AcceptDemand> savedDemands = new ArrayList<>();

        for (AcceptDemandDTO dto : dtos) {
            DispatchDetail dispatchDetail = dispatchDetailRepository.findById(dto.getDispatchId())
                    .orElseThrow(() -> new RuntimeException("Dispatch not found: " + dto.getDispatchId()));

            AcceptDemand acceptDemand = new AcceptDemand();
            acceptDemand.setDispatchDetail(dispatchDetail);
            acceptDemand.setReceivedPackets(dto.getReceivedPackets());
            acceptDemand.setRemarks(dto.getRemarks());
            acceptDemand.setQrCode(dto.getQrCode());

            savedDemands.add(acceptDemandRepository.save(acceptDemand));
        }
        return savedDemands;
    }

//    @Override
//    public List<AcceptDemand> getAllAcceptDemands() {
//        return acceptDemandRepository.findAll();
//    }

    @Override
    public List<AcceptDemandDTO> getAllAcceptDemands() {
        return acceptDemandRepository.findAll().stream().map(entity -> {
            AcceptDemandDTO dto = new AcceptDemandDTO();
            dto.setId(entity.getId());
            dto.setDispatchId(entity.getDispatchDetail().getId());
            dto.setReceivedPackets(entity.getReceivedPackets());
            dto.setRemarks(entity.getRemarks());
            dto.setQrCode(entity.getQrCode());
            return dto;
        }).collect(Collectors.toList());
    }

}
