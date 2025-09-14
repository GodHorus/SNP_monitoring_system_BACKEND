package com.example.master.services.impl;

import com.example.master.Dto.CDPOSupplierDispatchDTO;
import com.example.master.model.AcceptDemand;
import com.example.master.model.CDPOSupplierDispatch;
import com.example.master.model.Sector;
import com.example.master.repository.AcceptDemandRepository;
import com.example.master.repository.CDPOSupplierDispatchRepository;
import com.example.master.repository.SectorRepository;
import com.example.master.services.CDPOSupplierDispatchService;
import com.example.master.mapper.CDPOSupplierDispatchMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CDPOSupplierDispatchServiceImpl implements CDPOSupplierDispatchService {

    private final CDPOSupplierDispatchRepository dispatchRepository;
    private final AcceptDemandRepository acceptDemandRepository;
    private final SectorRepository sectorRepository;

    public CDPOSupplierDispatchServiceImpl(
            CDPOSupplierDispatchRepository dispatchRepository,
            AcceptDemandRepository acceptDemandRepository,
            SectorRepository sectorRepository) {
        this.dispatchRepository = dispatchRepository;
        this.acceptDemandRepository = acceptDemandRepository;
        this.sectorRepository = sectorRepository;
    }

    @Override
    public CDPOSupplierDispatchDTO createDispatch(CDPOSupplierDispatchDTO dto) {
        AcceptDemand acceptDemand = acceptDemandRepository.findById(dto.getAcceptDemandId())
                .orElseThrow(() -> new RuntimeException("AcceptDemand not found"));

        Sector sector = sectorRepository.findById(dto.getSectorId())
                .orElseThrow(() -> new RuntimeException("Sector not found: " + dto.getSectorId()));

        String lastSublotNo = dispatchRepository.findLastSublotNo();
        String newSublotNo = (lastSublotNo == null)
                ? "SUBLOT-1"
                : "SUBLOT-" + (Integer.parseInt(lastSublotNo.replace("SUBLOT-", "")) + 1);

        CDPOSupplierDispatch entity = CDPOSupplierDispatchMapper.toEntity(dto, acceptDemand, sector);
        entity.setSublotNo(newSublotNo);

        CDPOSupplierDispatch saved = dispatchRepository.save(entity);
        return CDPOSupplierDispatchMapper.toDTO(saved);
    }

    @Override
    public List<CDPOSupplierDispatchDTO> createDispatches(List<CDPOSupplierDispatchDTO> dtos) {
        String lastSublotNo = dispatchRepository.findLastSublotNo();
        int lastNumber = (lastSublotNo == null) ? 0 : Integer.parseInt(lastSublotNo.replace("SUBLOT-", ""));

        List<CDPOSupplierDispatchDTO> results = new ArrayList<>();

        for (CDPOSupplierDispatchDTO dto : dtos) {
            AcceptDemand acceptDemand = acceptDemandRepository.findById(dto.getAcceptDemandId())
                    .orElseThrow(() -> new RuntimeException("AcceptDemand not found: " + dto.getAcceptDemandId()));

            Sector sector = sectorRepository.findById(dto.getSectorId())
                    .orElseThrow(() -> new RuntimeException("Sector not found: " + dto.getSectorId()));

            lastNumber++;
            String newSublotNo = "SUBLOT-" + lastNumber;

            CDPOSupplierDispatch entity = CDPOSupplierDispatchMapper.toEntity(dto, acceptDemand, sector);
            entity.setSublotNo(newSublotNo);

            CDPOSupplierDispatch saved = dispatchRepository.save(entity);
            results.add(CDPOSupplierDispatchMapper.toDTO(saved));
        }

        return results;
    }

    @Override
    public CDPOSupplierDispatchDTO getDispatchById(Long id) {
        CDPOSupplierDispatch dispatch = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found"));

        return CDPOSupplierDispatchMapper.toDTO(dispatch);
    }

    @Override
    public List<CDPOSupplierDispatchDTO> getAllDispatches() {
        return dispatchRepository.findAll()
                .stream()
                .map(CDPOSupplierDispatchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDispatch(Long id) {
        dispatchRepository.deleteById(id);
    }
}
