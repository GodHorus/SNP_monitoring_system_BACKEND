package com.example.master.services.impl;

import com.example.master.Dto.AnganwadiCenterDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.*;
import com.example.master.repository.*;
import com.example.master.services.AnganwadiCenterService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnganwadiCenterServiceImpl implements AnganwadiCenterService {

    private final AnganwadiCenterRepository centerRepository;
    private final SupervisorRepository supervisorRepository;
    private final DistrictRepository districtRepository;
    private final BlockRepository blockRepository;

    public AnganwadiCenterServiceImpl(AnganwadiCenterRepository centerRepository,
                                      SupervisorRepository supervisorRepository,
                                      DistrictRepository districtRepository,
                                      BlockRepository blockRepository) {
        this.centerRepository = centerRepository;
        this.supervisorRepository = supervisorRepository;
        this.districtRepository = districtRepository;
        this.blockRepository = blockRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnganwadiCenterDTO> list(Pageable pageable) {
        return centerRepository.findAll(pageable).map(this::toDTO);
        // if you want everything without pagination, return:
        // new PageImpl<>(centerRepository.findAll().stream().map(this::toDTO).toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AnganwadiCenterDTO get(Long id) {
        AnganwadiCenter entity = centerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Anganwadi center not found: " + id));
        return toDTO(entity);
    }

    @Override
    public AnganwadiCenterDTO create(AnganwadiCenterDTO dto) {
        AnganwadiCenter entity = new AnganwadiCenter();
        apply(dto, entity);
        AnganwadiCenter saved = centerRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public AnganwadiCenterDTO update(Long id, AnganwadiCenterDTO dto) {
        AnganwadiCenter entity = centerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Anganwadi center not found: " + id));
        apply(dto, entity);
        AnganwadiCenter saved = centerRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (!centerRepository.existsById(id)) {
            throw new NotFoundException("Anganwadi center not found: " + id);
        }
        centerRepository.deleteById(id);
    }

    private AnganwadiCenterDTO toDTO(AnganwadiCenter e) {
        AnganwadiCenterDTO dto = new AnganwadiCenterDTO();
        dto.setId(e.getId());
        dto.setCenterId(e.getCenterId());
        dto.setCenterName(e.getCenterName());
        dto.setCenterAddress(e.getCenterAddress());
        dto.setStatus(e.getStatus());
        dto.setSupervisorName(e.getSupervisor() != null ? e.getSupervisor().getName() : null);
        dto.setDistrictName(e.getDistrict() != null ? e.getDistrict().getDistrictName() : null);
        dto.setBlockName(e.getBlock() != null ? e.getBlock().getBlockName() : null);
        return dto;
    }

    private void apply(AnganwadiCenterDTO dto, AnganwadiCenter e) {
        e.setCenterId(dto.getCenterId());
        e.setCenterName(dto.getCenterName());
        e.setCenterAddress(dto.getCenterAddress());
        e.setStatus(dto.getStatus() == null ? "active" : dto.getStatus());

        // resolve relations by name (as your UI passes names)
        if (dto.getSupervisorName() != null && !dto.getSupervisorName().isBlank()) {
            Supervisor s = supervisorRepository.findByName(dto.getSupervisorName())
                    .orElseGet(() -> supervisorRepository.save(new Supervisor(dto.getSupervisorName())));
            e.setSupervisor(s);
        } else {
            e.setSupervisor(null);
        }

        if (dto.getDistrictName() != null && !dto.getDistrictName().isBlank()) {
            District d = districtRepository.findByDistrictName(dto.getDistrictName())
                    .orElseGet(() -> districtRepository.save(new District(dto.getDistrictName())));
            e.setDistrict(d);
        } else {
            e.setDistrict(null);
        }

        if (dto.getBlockName() != null && !dto.getBlockName().isBlank()) {
            Block b = blockRepository.findByBlockName(dto.getBlockName())
                    .orElseGet(() -> blockRepository.save(new Block(dto.getBlockName())));
            e.setBlock(b);
        } else {
            e.setBlock(null);
        }
    }
}
