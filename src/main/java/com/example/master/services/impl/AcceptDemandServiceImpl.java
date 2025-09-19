package com.example.master.services.impl;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.Dto.CDPOSupplierDispatchDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.model.Demand;
import com.example.master.repository.AcceptDemandRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.services.AcceptDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcceptDemandServiceImpl implements AcceptDemandService {

    @Autowired
    private AcceptDemandRepository repository;

    @Override
    public AcceptDemandDTO create(AcceptDemandDTO dto) {
        AcceptDemand entity = new AcceptDemand();
        entity.setDemandId(dto.getDemandId());
        entity.setBatchNo(dto.getBatchNo());
        entity.setLotNo(dto.getLotNo());
        entity.setCdpo(dto.getCdpo());
        entity.setNoOfPackets(dto.getNoOfPackets());
        entity.setReceivedPackets(dto.getReceivedPackets());
        entity.setRemarks(dto.getRemarks());
        entity.setQrCode(dto.getQrCode());

        entity = repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<AcceptDemandDTO> getByDemandId(Long demandId) {
        return repository.findByDemandId(demandId).stream().map(entity -> {
            AcceptDemandDTO dto = new AcceptDemandDTO();
            dto.setId(entity.getId());
            dto.setDemandId(entity.getDemandId());
            dto.setBatchNo(entity.getBatchNo());
            dto.setLotNo(entity.getLotNo());
            dto.setCdpo(entity.getCdpo());
            dto.setNoOfPackets(entity.getNoOfPackets());
            dto.setReceivedPackets(entity.getReceivedPackets());
            dto.setRemarks(entity.getRemarks());
            dto.setQrCode(entity.getQrCode());
            return dto;
        }).collect(Collectors.toList());
    }
}
