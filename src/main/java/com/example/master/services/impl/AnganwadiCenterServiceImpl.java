package com.example.master.services.impl;

import com.example.master.Dto.AnganwadiCenterDTO;
import com.example.master.model.AnganwadiCenter;
import com.example.master.model.Supervisor;
import com.example.master.model.Cdpo;
import com.example.master.model.District;
import com.example.master.repository.AnganwadiCenterRepository;
import com.example.master.repository.SupervisorRepository;
import com.example.master.repository.CdpoRepository;
import com.example.master.repository.DistrictRepository;
import com.example.master.services.AnganwadiCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AnganwadiCenterServiceImpl implements AnganwadiCenterService {

    private final AnganwadiCenterRepository repository;
    private final SupervisorRepository supervisorRepository;
    private final CdpoRepository cdpoRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public AnganwadiCenterServiceImpl(AnganwadiCenterRepository repository,
                                      SupervisorRepository supervisorRepository,
                                      CdpoRepository cdpoRepository,
                                      DistrictRepository districtRepository) {
        this.repository = repository;
        this.supervisorRepository = supervisorRepository;
        this.cdpoRepository = cdpoRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public AnganwadiCenterDTO create(AnganwadiCenterDTO dto) {
        AnganwadiCenter entity = new AnganwadiCenter();
        entity.setCenterName(dto.getCenterName());
        entity.setCenterAddress(dto.getCenterAddress());
        entity.setStatus(dto.getStatus());

        // Fetch related entities
        if (dto.getSupervisorId() != null) {
            Supervisor supervisor = supervisorRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new RuntimeException("Supervisor not found"));
            entity.setSupervisor(supervisor);
            dto.setSupervisorId(supervisor.getId());  // Add this line
        }

        if (dto.getCdpoId() != null) {
            Cdpo cdpo = cdpoRepository.findById(dto.getCdpoId())
                    .orElseThrow(() -> new RuntimeException("CDPO not found"));
            entity.setCdpo(cdpo);
            dto.setCdpoId(cdpo.getId());  // Add this line
        }

        if (dto.getDistrictId() != null) {
            District district = districtRepository.findById(dto.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found"));
            entity.setDistrict(district);
            dto.setDistrictId(district.getId());  // Add this line
        }

        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public AnganwadiCenterDTO update(Long id, AnganwadiCenterDTO dto) {
        AnganwadiCenter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AnganwadiCenter not found"));

        entity.setCenterName(dto.getCenterName());
        entity.setCenterAddress(dto.getCenterAddress());
        entity.setStatus(dto.getStatus());

        if (dto.getSupervisorId() != null) {
            Supervisor supervisor = supervisorRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new RuntimeException("Supervisor not found"));
            entity.setSupervisor(supervisor);
            dto.setSupervisorId(supervisor.getId());
        }

        if (dto.getCdpoId() != null) {
            Cdpo cdpo = cdpoRepository.findById(dto.getCdpoId())
                    .orElseThrow(() -> new RuntimeException("CDPO not found"));
            entity.setCdpo(cdpo);
            dto.setCdpoId(cdpo.getId());
        }

        if (dto.getDistrictId() != null) {
            District district = districtRepository.findById(dto.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found"));
            entity.setDistrict(district);
            dto.setDistrictId(district.getId());
        }

        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AnganwadiCenterDTO get(Long id) {
        AnganwadiCenter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anganwadi not found"));
        AnganwadiCenterDTO dto = new AnganwadiCenterDTO(entity.getId(), entity.getCenterName(), entity.getCenterAddress(), entity.getStatus());

        if (entity.getSupervisor() != null) {
            dto.setSupervisorId(entity.getSupervisor().getId());
        }
        if (entity.getCdpo() != null) {
            dto.setCdpoId(entity.getCdpo().getId());
        }
        if (entity.getDistrict() != null) {
            dto.setDistrictId(entity.getDistrict().getId());
        }

        return dto;
    }

    @Override
    public Page<AnganwadiCenterDTO> list(Pageable pageable) {
        return repository.findAll(pageable)
                .map(e -> {
                    // Create the AnganwadiCenterDTO and populate basic fields
                    AnganwadiCenterDTO dto = new AnganwadiCenterDTO(e.getId(), e.getCenterName(), e.getCenterAddress(), e.getStatus() );

                    // Set the supervisorId if supervisor is present
                    if (e.getSupervisor() != null) {
                        dto.setSupervisorId(e.getSupervisor().getId());
                    }

                    // Set the cdpoId if cdpo is present
                    if (e.getCdpo() != null) {
                        dto.setCdpoId(e.getCdpo().getId());
                    }

                    // Set the districtId if district is present
                    if (e.getDistrict() != null) {
                        dto.setDistrictId(e.getDistrict().getId());
                    }

                    return dto;
                });
    }

}
