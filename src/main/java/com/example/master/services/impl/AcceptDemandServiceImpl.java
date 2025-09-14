package com.example.master.services.impl;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.Dto.CDPOSupplierDispatchDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.model.Demand;
import com.example.master.repository.AcceptDemandRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.services.AcceptDemandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptDemandServiceImpl implements AcceptDemandService {

    private final AcceptDemandRepository acceptDemandRepository;
    private final DemandRepository demandRepository;

    public AcceptDemandServiceImpl(AcceptDemandRepository acceptDemandRepository,
                                   DemandRepository demandRepository) {
        this.acceptDemandRepository = acceptDemandRepository;
        this.demandRepository = demandRepository;
    }

    @Override
    public List<AcceptDemand> createAcceptDemands(List<AcceptDemandDTO> dtos) {
        List<AcceptDemand> savedDemands = new ArrayList<>();

        for (AcceptDemandDTO dto : dtos) {
            Demand demand = demandRepository.findById(dto.getDemandId())
                    .orElseThrow(() -> new RuntimeException("Demand not found: " + dto.getDemandId()));

            AcceptDemand acceptDemand = new AcceptDemand();
            acceptDemand.setDemand(demand);
            acceptDemand.setReceivedPackets(dto.getReceivedPackets());
            acceptDemand.setRemarks(dto.getRemarks());
            acceptDemand.setQrCode(dto.getQrCode());

            savedDemands.add(acceptDemandRepository.save(acceptDemand));
        }
        return savedDemands;
    }

//    @Override
//    public Optional<AcceptDemandDTO> getAcceptDemandByDemandId(Long demandId) {
//        return acceptDemandRepository.findByDemandId(demandId)
//                .map(entity -> {
//                    AcceptDemandDTO dto = new AcceptDemandDTO();
//                    dto.setId(entity.getId());
//                    dto.setDemandId(entity.getDemand().getId());
//                    dto.setReceivedPackets(entity.getReceivedPackets());
//                    dto.setRemarks(entity.getRemarks());
//                    dto.setQrCode(entity.getQrCode());
//                    return dto;
//                });
//    }

    @Override
    public Optional<AcceptDemandDTO> getAcceptDemandByDemandId(Long demandId) {
        return acceptDemandRepository.findByDemandId(demandId)
                .map(entity -> {
                    AcceptDemandDTO dto = new AcceptDemandDTO();
                    dto.setId(entity.getId());
                    dto.setDemandId(entity.getDemand().getId());
                    dto.setReceivedPackets(entity.getReceivedPackets());
                    dto.setRemarks(entity.getRemarks());
                    dto.setQrCode(entity.getQrCode());

                    // Map nested CDPOSupplierDispatch
                    List<CDPOSupplierDispatchDTO> dispatchDTOs = entity.getCdpoSupplierDispatches().stream()
                            .map(dispatch -> {
                                CDPOSupplierDispatchDTO dispatchDTO = new CDPOSupplierDispatchDTO();
                                dispatchDTO.setId(dispatch.getId());
                                dispatchDTO.setDispatchPackets(dispatch.getDispatchPackets());
                                dispatchDTO.setRemarks(dispatch.getRemarks());
                                dispatchDTO.setSublotNo(dispatch.getSublotNo());
                                if (dispatch.getSector() != null) {
                                    dispatchDTO.setSectorId(dispatch.getSector().getId());
                                }
                                return dispatchDTO;
                            })
                            .toList();

                    dto.setCdpoSupplierDispatches(dispatchDTOs);

                    return dto;
                });
    }



    @Override
    public List<AcceptDemandDTO> getAllAcceptDemands() {
        return acceptDemandRepository.findAll().stream().map(entity -> {
            AcceptDemandDTO dto = new AcceptDemandDTO();
            dto.setId(entity.getId());
            dto.setDemandId(entity.getDemand().getId());
            dto.setReceivedPackets(entity.getReceivedPackets());
            dto.setRemarks(entity.getRemarks());
            dto.setQrCode(entity.getQrCode());
            return dto;
        }).collect(Collectors.toList());
    }
}
