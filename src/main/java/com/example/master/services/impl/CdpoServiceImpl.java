package com.example.master.services.impl;

import com.example.master.Dto.CdpoDTO;
import com.example.master.Dto.SectorDTO;
import com.example.master.Dto.AnganwadiCenterDTO;
import com.example.master.model.Cdpo;
import com.example.master.model.Sector;
import com.example.master.model.AnganwadiCenter;
import com.example.master.repository.CdpoRepository;
import com.example.master.repository.SectorRepository;
import com.example.master.services.CdpoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CdpoServiceImpl implements CdpoService {

    private final CdpoRepository cdpoRepository;

    public CdpoServiceImpl(CdpoRepository cdpoRepository) {
        this.cdpoRepository = cdpoRepository;
    }

    @Override
    public List<CdpoDTO> getAllCdpos() {
        return cdpoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CdpoDTO getCdpoById(Long id) {
        return cdpoRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Cdpo not found with id " + id));
    }

    @Override
    public CdpoDTO createCdpo(CdpoDTO cdpoDTO) {
        Cdpo cdpo = new Cdpo();
        cdpo.setCdpoName(cdpoDTO.getCdpoName());
        // For simplicity, sectors creation can be added if needed
        Cdpo saved = cdpoRepository.save(cdpo);
        return mapToDto(saved);
    }

    @Override
    public CdpoDTO updateCdpo(Long id, CdpoDTO cdpoDTO) {
        Cdpo cdpo = cdpoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cdpo not found with id " + id));
        cdpo.setCdpoName(cdpoDTO.getCdpoName());
        // Update sectors if required
        Cdpo updated = cdpoRepository.save(cdpo);
        return mapToDto(updated);
    }

    @Override
    public void deleteCdpo(Long id) {
        cdpoRepository.deleteById(id);
    }

    // Helper: map Cdpo entity to DTO
    private CdpoDTO mapToDto(Cdpo cdpo) {
        CdpoDTO dto = new CdpoDTO();
        dto.setId(cdpo.getId());
        dto.setCdpoName(cdpo.getCdpoName());
        if(cdpo.getSectors() != null) {
            List<SectorDTO> sectors = cdpo.getSectors().stream().map(this::mapSectorToDto).collect(Collectors.toList());
            dto.setSectors(sectors);
        }
        return dto;
    }

    private SectorDTO mapSectorToDto(Sector sector) {
        SectorDTO dto = new SectorDTO();
        dto.setId(sector.getId());
        dto.setCdpoId(sector.getCdpo() != null ? sector.getCdpo().getId() : null);
        dto.setName(sector.getName());
        dto.setStatus(sector.getStatus());
        if(sector.getAnganwadiCenters() != null) {
            List<AnganwadiCenterDTO> centers = sector.getAnganwadiCenters().stream().map(this::mapCenterToDto).collect(Collectors.toList());
            dto.setAnganwadiCenters(centers);
        }
        return dto;
    }

    private AnganwadiCenterDTO mapCenterToDto(AnganwadiCenter center) {
        AnganwadiCenterDTO dto = new AnganwadiCenterDTO();
        dto.setId(center.getId());
        dto.setCenterName(center.getCenterName());
        dto.setCenterAddress(center.getCenterAddress());
        dto.setStatus(center.getStatus());
        dto.setSupervisorId(center.getSupervisor() != null ? center.getSupervisor().getId() : null);
        return dto;
    }
}
