package com.example.master.services.impl;

import com.example.master.Dto.CDPOSupplierDispatchDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.model.CDPOSupplierDispatch;
import com.example.master.repository.AcceptDemandRepository;
import com.example.master.repository.CDPOSupplierDispatchRepository;
import com.example.master.services.CDPOSupplierDispatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CDPOSupplierDispatchServiceImpl implements CDPOSupplierDispatchService {

    private final CDPOSupplierDispatchRepository dispatchRepository;
    private final AcceptDemandRepository acceptDemandRepository;

    public CDPOSupplierDispatchServiceImpl(CDPOSupplierDispatchRepository dispatchRepository,
                                           AcceptDemandRepository acceptDemandRepository) {
        this.dispatchRepository = dispatchRepository;
        this.acceptDemandRepository = acceptDemandRepository;
    }

    @Override
    public CDPOSupplierDispatchDTO createDispatch(CDPOSupplierDispatchDTO dto) {
        AcceptDemand acceptDemand = acceptDemandRepository.findById(dto.getAcceptDemandId())
                .orElseThrow(() -> new RuntimeException("AcceptDemand not found"));

        // 🔹 Fetch last sublotNo
        String lastSublotNo = dispatchRepository.findLastSublotNo();

        // 🔹 Generate new sublotNo
        String newSublotNo;
        if (lastSublotNo == null) {
            newSublotNo = "SUBLOT-1";   // first entry
        } else {
            // Extract number part
            int lastNumber = Integer.parseInt(lastSublotNo.replace("SUBLOT-", ""));
            newSublotNo = "SUBLOT-" + (lastNumber + 1);
        }

        // 🔹 Save new Dispatch
        CDPOSupplierDispatch dispatch = new CDPOSupplierDispatch();
        dispatch.setDispatchPackets(dto.getDispatchPackets());
        dispatch.setRemarks(dto.getRemarks());
        dispatch.setAcceptDemand(acceptDemand);
        dispatch.setSublotNo(newSublotNo);

        CDPOSupplierDispatch saved = dispatchRepository.save(dispatch);

        // Map back to DTO
        dto.setId(saved.getId());
        dto.setAcceptDemandId(saved.getAcceptDemand().getId());
        return dto;
    }


    @Override
    public CDPOSupplierDispatchDTO getDispatchById(Long id) {
        CDPOSupplierDispatch dispatch = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found"));

        return mapToDTO(dispatch);
    }

    @Override
    public List<CDPOSupplierDispatchDTO> getAllDispatches() {
        return dispatchRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDispatch(Long id) {
        dispatchRepository.deleteById(id);
    }

    private CDPOSupplierDispatchDTO mapToDTO(CDPOSupplierDispatch dispatch) {
        CDPOSupplierDispatchDTO dto = new CDPOSupplierDispatchDTO();
        dto.setId(dispatch.getId());
        dto.setDispatchPackets(dispatch.getDispatchPackets());
        dto.setRemarks(dispatch.getRemarks());
        dto.setAcceptDemandId(dispatch.getAcceptDemand().getId());
        dto.setSublotNo(dispatch.getSublotNo());
        return dto;
    }
}
