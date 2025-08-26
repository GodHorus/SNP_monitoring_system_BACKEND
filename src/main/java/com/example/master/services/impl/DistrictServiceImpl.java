package com.example.master.services.impl;

import com.example.master.Dto.*;
import com.example.master.model.*;
import com.example.master.repository.DistrictRepository;
import com.example.master.services.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<DistrictDTO> getAllDistricts() {
        return districtRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DistrictDTO convertToDTO(District district) {
        DistrictDTO dto = new DistrictDTO();
        dto.setId(district.getId());
        dto.setDistrictName(district.getDistrictName());

        dto.setCdpos(
                district.getCdpos().stream().map(cdpo -> {
                    CdpoDTO cdpoDTO = new CdpoDTO();
                    cdpoDTO.setId(cdpo.getId());
                    cdpoDTO.setCdpoName(cdpo.getCdpoName());

                    cdpoDTO.setSupervisors(
                            cdpo.getSupervisors().stream().map(s -> {
                                SupervisorDTO supDTO = new SupervisorDTO();
                                supDTO.setId(s.getId());
                                supDTO.setName(s.getName());

                                supDTO.setAnganwadiCenters(
                                        s.getAnganwadiCenters().stream().map(a -> {
                                            AnganwadiCenterDTO ac = new AnganwadiCenterDTO();
                                            ac.setId(a.getId());
                                            ac.setCenterName(a.getCenterName());
                                            ac.setCenterAddress(a.getCenterAddress());
                                            ac.setStatus(a.getStatus());
                                            return ac;
                                        }).collect(Collectors.toList())
                                );
                                return supDTO;
                            }).collect(Collectors.toList())
                    );
                    return cdpoDTO;
                }).collect(Collectors.toList())
        );

        return dto;
    }
}
