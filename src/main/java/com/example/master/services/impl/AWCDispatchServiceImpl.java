package com.example.master.services.impl;

import com.example.master.Dto.AWCDispatchDTO;
import com.example.master.model.AWCDispatch;
import com.example.master.model.AnganwadiCenter;
import com.example.master.model.Demand;
import com.example.master.repository.AWCDispatchRepository;
import com.example.master.repository.AnganwadiCenterRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.services.AWCDispatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWCDispatchServiceImpl implements AWCDispatchService {

    private final AWCDispatchRepository dispatchRepository;
    private final AnganwadiCenterRepository centerRepository;
    private final DemandRepository demandRepository;

    public AWCDispatchServiceImpl(AWCDispatchRepository dispatchRepository,
                                  AnganwadiCenterRepository centerRepository,
                                  DemandRepository demandRepository) {
        this.dispatchRepository = dispatchRepository;
        this.centerRepository = centerRepository;
        this.demandRepository = demandRepository;
    }

    @Override
    public AWCDispatchDTO createDispatch(AWCDispatchDTO dto) {
        AWCDispatch entity = mapDtoToEntity(dto);
        return mapEntityToDto(dispatchRepository.save(entity));
    }

    @Override
    public List<AWCDispatchDTO> createDispatches(List<AWCDispatchDTO> dtos) {
        List<AWCDispatch> entities = dtos.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());

        List<AWCDispatch> savedEntities = dispatchRepository.saveAll(entities);
        return savedEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AWCDispatchDTO getDispatchById(Long id) {
        return dispatchRepository.findById(id)
                .map(this::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException("Dispatch not found with id " + id));
    }

    @Override
    public List<AWCDispatchDTO> getAllDispatches() {
        return dispatchRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AWCDispatchDTO updateDispatch(Long id, AWCDispatchDTO dto) {
        AWCDispatch existing = dispatchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispatch not found with id " + id));

        existing.setSublotNo(dto.getSublotNo());
        existing.setBenficiaryCount(dto.getBenficiaryCount());
        existing.setDistributedPackets(dto.getDistributedPackets());

        // Update demand if provided
        if (dto.getDemandId() != null) {
            Demand demand = demandRepository.findById(dto.getDemandId())
                    .orElseThrow(() -> new EntityNotFoundException("Demand not found: " + dto.getDemandId()));
            existing.setDemand(demand);
        }

        // Update Anganwadi center if provided
        if (dto.getAnganwadiId() != null) {
            AnganwadiCenter center = centerRepository.findById(dto.getAnganwadiId())
                    .orElseThrow(() -> new EntityNotFoundException("Anganwadi not found: " + dto.getAnganwadiId()));
            existing.setAnganwadiCenter(center);
        }

        return mapEntityToDto(dispatchRepository.save(existing));
    }

    @Override
    public void deleteDispatch(Long id) {
        if (!dispatchRepository.existsById(id)) {
            throw new EntityNotFoundException("Dispatch not found with id " + id);
        }
        dispatchRepository.deleteById(id);
    }

    // ====================== Mapper Methods ======================
    private AWCDispatchDTO mapEntityToDto(AWCDispatch entity) {
        AWCDispatchDTO dto = new AWCDispatchDTO();
        dto.setId(entity.getId());
        dto.setSublotNo(entity.getSublotNo());
        dto.setBenficiaryCount(entity.getBenficiaryCount());
        dto.setDistributedPackets(entity.getDistributedPackets());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getDemand() != null) {
            dto.setDemandId(entity.getDemand().getId());
//            dto.setDemandName(entity.getDemand().getName()); // optional
        }

        if (entity.getAnganwadiCenter() != null) {
            dto.setAnganwadiId(entity.getAnganwadiCenter().getId());
            dto.setAnganwadiName(entity.getAnganwadiCenter().getCenterName());
        }

        return dto;
    }

    private AWCDispatch mapDtoToEntity(AWCDispatchDTO dto) {
        AWCDispatch entity = new AWCDispatch();
        entity.setSublotNo(dto.getSublotNo());
        entity.setBenficiaryCount(dto.getBenficiaryCount());
        entity.setDistributedPackets(dto.getDistributedPackets());

        if (dto.getDemandId() != null) {
            Demand demand = demandRepository.findById(dto.getDemandId())
                    .orElseThrow(() -> new EntityNotFoundException("Demand not found: " + dto.getDemandId()));
            entity.setDemand(demand);
        }

        if (dto.getAnganwadiId() != null) {
            AnganwadiCenter center = centerRepository.findById(dto.getAnganwadiId())
                    .orElseThrow(() -> new EntityNotFoundException("Anganwadi not found: " + dto.getAnganwadiId()));
            entity.setAnganwadiCenter(center);
        }

        return entity;
    }
}
