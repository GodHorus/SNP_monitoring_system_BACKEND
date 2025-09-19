package com.example.master.services.impl;

import com.example.master.Dto.CDPOSupplierDispatchDTO;
import com.example.master.model.CDPOSupplierDispatch;
import com.example.master.repository.CDPOSupplierDispatchRepository;
import com.example.master.services.CDPOSupplierDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CDPOSupplierDispatchServiceImpl implements CDPOSupplierDispatchService {

    @Autowired
    private CDPOSupplierDispatchRepository repository;

    private String generateNextSublotNo() {
        String lastSublotNo = repository.findLastSublotNo();
        if (lastSublotNo == null) {
            return "SUBLOT-1";
        }
        try {
            int lastNumber = Integer.parseInt(lastSublotNo.replace("SUBLOT-", ""));
            return "SUBLOT-" + (lastNumber + 1);
        } catch (NumberFormatException e) {
            return "SUBLOT-1"; // fallback if malformed
        }
    }

    @Override
    public CDPOSupplierDispatchDTO create(CDPOSupplierDispatchDTO dto) {
        CDPOSupplierDispatch entity = new CDPOSupplierDispatch();
        entity.setDemandId(dto.getDemandId());
        entity.setBatchNo(dto.getBatchNo());
        entity.setLotNo(dto.getLotNo());
        entity.setCdpo(dto.getCdpo());
        entity.setSector(dto.getSector());
        entity.setDispatchPackets(dto.getDispatchPackets());
        entity.setRemarks(dto.getRemarks());

        // ✅ auto-generate sublot number
        entity.setSublotNo(generateNextSublotNo());

        entity = repository.save(entity);

        dto.setId(entity.getId());
        dto.setSublotNo(entity.getSublotNo()); // return generated sublot
        return dto;
    }

    @Override
    public List<CDPOSupplierDispatchDTO> getByDemandId(Long demandId) {
        return repository.findByDemandId(demandId).stream().map(entity -> {
            CDPOSupplierDispatchDTO dto = new CDPOSupplierDispatchDTO();
            dto.setId(entity.getId());
            dto.setDemandId(entity.getDemandId());
            dto.setBatchNo(entity.getBatchNo());
            dto.setLotNo(entity.getLotNo());
            dto.setCdpo(entity.getCdpo());
            dto.setSector(entity.getSector());
            dto.setDispatchPackets(entity.getDispatchPackets());
            dto.setRemarks(entity.getRemarks());
            dto.setSublotNo(entity.getSublotNo());
            return dto;
        }).collect(Collectors.toList());
    }
}
