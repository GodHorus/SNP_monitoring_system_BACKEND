package com.example.master.services.impl;

import com.example.master.Dto.AWCDistributeDTO;
import com.example.master.model.AWCDistribute;
import com.example.master.model.DispatchDetail;
import com.example.master.repository.AWCDistributeRepository;
import com.example.master.repository.DispatchDetailRepository;
import com.example.master.services.AWCDistributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWCDistributeServiceImpl implements AWCDistributeService {

    @Autowired
    private AWCDistributeRepository repository;

    @Autowired
    private DispatchDetailRepository dispatchRepository;

    private AWCDistributeDTO mapToDTO(AWCDistribute entity) {
        AWCDistributeDTO dto = new AWCDistributeDTO();
        dto.setId(entity.getId());
        dto.setSublot(entity.getSublot());
        dto.setBenificiary(entity.getBenificiary());
        dto.setHcm(entity.getHcm());
        dto.setThr(entity.getThr());
        dto.setDispatchId(entity.getDispatchDetail().getId());
        return dto;
    }

    private AWCDistribute mapToEntity(AWCDistributeDTO dto) {
        AWCDistribute entity = new AWCDistribute();
        entity.setSublot(dto.getSublot());
        entity.setBenificiary(dto.getBenificiary());
        entity.setHcm(dto.getHcm());
        entity.setThr(dto.getThr());
        DispatchDetail dispatch = dispatchRepository.findById(dto.getDispatchId())
                .orElseThrow(() -> new RuntimeException("DispatchDetail not found with id " + dto.getDispatchId()));
        entity.setDispatchDetail(dispatch);
        return entity;
    }

    @Override
    public AWCDistributeDTO createAWCDistribute(AWCDistributeDTO dto) {
        AWCDistribute saved = repository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    @Override
    public AWCDistributeDTO getAWCDistributeById(Long id) {
        AWCDistribute entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AWCDistribute not found with id " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<AWCDistributeDTO> getAllAWCDistributes() {
        return repository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public AWCDistributeDTO updateAWCDistribute(Long id, AWCDistributeDTO dto) {
        AWCDistribute entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AWCDistribute not found with id " + id));
        entity.setSublot(dto.getSublot());
        entity.setBenificiary(dto.getBenificiary());
        entity.setHcm(dto.getHcm());
        entity.setThr(dto.getThr());
        if (dto.getDispatchId() != null) {
            DispatchDetail dispatch = dispatchRepository.findById(dto.getDispatchId())
                    .orElseThrow(() -> new RuntimeException("DispatchDetail not found with id " + dto.getDispatchId()));
            entity.setDispatchDetail(dispatch);
        }
        return mapToDTO(repository.save(entity));
    }

    @Override
    public void deleteAWCDistribute(Long id) {
        repository.deleteById(id);
    }
}
