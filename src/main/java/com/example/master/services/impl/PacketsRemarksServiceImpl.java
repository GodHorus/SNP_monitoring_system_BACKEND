package com.example.master.services.impl;

import com.example.master.Dto.PacketsRemarksDTO;
import com.example.master.model.AWCDispatch;
import com.example.master.model.PacketsRemarks;
import com.example.master.repository.AWCDispatchRepository;
import com.example.master.repository.PacketsRemarksRepository;
import com.example.master.services.PacketsRemarksService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacketsRemarksServiceImpl implements PacketsRemarksService {

    private final PacketsRemarksRepository repository;
    private final AWCDispatchRepository awcDispatchRepository;

    public PacketsRemarksServiceImpl(PacketsRemarksRepository repository,
                                     AWCDispatchRepository awcDispatchRepository) {
        this.repository = repository;
        this.awcDispatchRepository = awcDispatchRepository;
    }

    private PacketsRemarksDTO mapToDTO(PacketsRemarks entity) {
        PacketsRemarksDTO dto = new PacketsRemarksDTO();
        dto.setId(entity.getId());
        dto.setAwcDispatchId(entity.getAwcDispatch().getId());
        dto.setRemark(entity.getRemark());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private PacketsRemarks mapToEntity(PacketsRemarksDTO dto) {
        PacketsRemarks entity = new PacketsRemarks();
//        if (dto.getId() != null) entity.setId(dto.getId());
        entity.setRemark(dto.getRemark());
        AWCDispatch awcDispatch = awcDispatchRepository.findById(dto.getAwcDispatchId())
                .orElseThrow(() -> new RuntimeException("AWCDispatch not found"));
        entity.setAwcDispatch(awcDispatch);
        return entity;
    }

    @Override
    public PacketsRemarksDTO create(PacketsRemarksDTO dto) {
        PacketsRemarks entity = mapToEntity(dto);
        return mapToDTO(repository.save(entity));
    }

    @Override
    public PacketsRemarksDTO update(Long id, PacketsRemarksDTO dto) {
        PacketsRemarks entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PacketsRemarks not found"));
        entity.setRemark(dto.getRemark());
        return mapToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PacketsRemarksDTO getById(Long id) {
        return repository.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("PacketsRemarks not found"));
    }

    @Override
    public List<PacketsRemarksDTO> getByAwcDispatchId(Long awcDispatchId) {
        return repository.findByAwcDispatch_Id(awcDispatchId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PacketsRemarksDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
