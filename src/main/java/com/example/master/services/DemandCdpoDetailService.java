package com.example.master.services;

import com.example.master.Dto.DemandCdpoDetailRequestDTO;
import com.example.master.Dto.DemandCdpoDetailResponseDTO;

import java.util.List;

public interface DemandCdpoDetailService {
    DemandCdpoDetailResponseDTO createDetail(DemandCdpoDetailRequestDTO dto);
    List<DemandCdpoDetailResponseDTO> createMultipleDetails(List<DemandCdpoDetailRequestDTO> dtos);
    List<DemandCdpoDetailResponseDTO> getDetailsByDemand(Long demandId);
}
