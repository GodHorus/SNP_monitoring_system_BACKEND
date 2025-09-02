package com.example.master.services.impl;

import com.example.master.Dto.AWCAcceptDemandDTO;
import com.example.master.model.AWCAcceptDemand;
import com.example.master.model.DispatchDetail;
import com.example.master.repository.AWCAcceptDemandRepository;
import com.example.master.repository.DispatchDetailRepository;
import com.example.master.services.AWCAcceptDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWCAcceptDemandServiceImpl implements AWCAcceptDemandService {

    @Autowired
    private AWCAcceptDemandRepository repository;

    @Autowired
    private DispatchDetailRepository dispatchRepository;

    private AWCAcceptDemandDTO mapToDTO(AWCAcceptDemand entity) {
        AWCAcceptDemandDTO dto = new AWCAcceptDemandDTO();
        dto.setId(entity.getId());
        dto.setRecievednumber(entity.getRecievednumber());
        dto.setRemarks(entity.getRemarks());
        dto.setDispatchId(entity.getDispatchDetail().getId());
        return dto;
    }

    private AWCAcceptDemand mapToEntity(AWCAcceptDemandDTO dto) {
        AWCAcceptDemand entity = new AWCAcceptDemand();
        entity.setRecievednumber(dto.getRecievednumber());
        entity.setRemarks(dto.getRemarks());
        DispatchDetail dispatch = dispatchRepository.findById(dto.getDispatchId())
                .orElseThrow(() -> new RuntimeException("DispatchDetail not found with id " + dto.getDispatchId()));
        entity.setDispatchDetail(dispatch);
        return entity;
    }

    @Override
    public AWCAcceptDemandDTO createAcceptDemand(AWCAcceptDemandDTO dto) {
        AWCAcceptDemand saved = repository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    @Override
    public List<AWCAcceptDemandDTO> createAcceptDemands(List<AWCAcceptDemandDTO> dtos) {
        List<AWCAcceptDemand> entities = dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        List<AWCAcceptDemand> savedEntities = repository.saveAll(entities);
        return savedEntities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public AWCAcceptDemandDTO getAcceptDemandById(Long id) {
        AWCAcceptDemand entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AWCAcceptDemand not found with id " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<AWCAcceptDemandDTO> getAllAcceptDemands() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public AWCAcceptDemandDTO updateAcceptDemand(Long id, AWCAcceptDemandDTO dto) {
        AWCAcceptDemand entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AWCAcceptDemand not found with id " + id));
        entity.setRecievednumber(dto.getRecievednumber());
        entity.setRemarks(dto.getRemarks());
        if (dto.getDispatchId() != null) {
            DispatchDetail dispatch = dispatchRepository.findById(dto.getDispatchId())
                    .orElseThrow(() -> new RuntimeException("DispatchDetail not found with id " + dto.getDispatchId()));
            entity.setDispatchDetail(dispatch);
        }
        return mapToDTO(repository.save(entity));
    }

    @Override
    public void deleteAcceptDemand(Long id) {
        repository.deleteById(id);
    }
}
