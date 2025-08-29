package com.example.master.services.impl;

import com.example.master.Dto.FciDTO;
import com.example.master.model.Fci;
import com.example.master.repository.FciRepository;
import com.example.master.services.FciService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FciServiceImpl implements FciService {

    private final FciRepository fciRepository;

    public FciServiceImpl(FciRepository fciRepository) {
        this.fciRepository = fciRepository;
    }

    private FciDTO convertToDTO(Fci fci) {
        return new FciDTO(fci.getId(), fci.getName());
    }

    private Fci convertToEntity(FciDTO dto) {
        return new Fci(dto.getId(), dto.getName());
    }

    @Override
    public List<FciDTO> getAllFci() {
        return fciRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FciDTO getFciById(Long id) {
        return fciRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public FciDTO createFci(FciDTO fciDTO) {
        Fci fci = convertToEntity(fciDTO);
        Fci saved = fciRepository.save(fci);
        return convertToDTO(saved);
    }

    @Override
    public FciDTO updateFci(Long id, FciDTO fciDTO) {
        return fciRepository.findById(id)
                .map(existing -> {
                    existing.setName(fciDTO.getName());
                    Fci updated = fciRepository.save(existing);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void deleteFci(Long id) {
        fciRepository.deleteById(id);
    }
}
