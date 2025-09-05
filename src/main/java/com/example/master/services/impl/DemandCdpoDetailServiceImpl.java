package com.example.master.services.impl;

import com.example.master.Dto.DemandCdpoDetailRequestDTO;
import com.example.master.Dto.DemandCdpoDetailResponseDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.Cdpo;
import com.example.master.model.Demand;
import com.example.master.model.DemandCdpoDetail;
import com.example.master.repository.CdpoRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.repository.DemandCdpoDetailRepository;
import com.example.master.services.DemandCdpoDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandCdpoDetailServiceImpl implements DemandCdpoDetailService {

    private final DemandCdpoDetailRepository detailRepository;
    private final DemandRepository demandRepository;
    private final CdpoRepository cdpoRepository;

    public DemandCdpoDetailServiceImpl(DemandCdpoDetailRepository detailRepository,
                                       DemandRepository demandRepository,
                                       CdpoRepository cdpoRepository) {
        this.detailRepository = detailRepository;
        this.demandRepository = demandRepository;
        this.cdpoRepository = cdpoRepository;
    }

    @Override
    public DemandCdpoDetailResponseDTO createDetail(DemandCdpoDetailRequestDTO dto) {
        Demand demand = demandRepository.findById(dto.getDemandId())
                .orElseThrow(() -> new NotFoundException("Demand not found"));

        Cdpo cdpo = cdpoRepository.findById(dto.getCdpoId())
                .orElseThrow(() -> new NotFoundException("CDPO not found"));

        DemandCdpoDetail detail = new DemandCdpoDetail();
        detail.setDemand(demand);
        detail.setCdpo(cdpo);
        detail.setQuantity(dto.getQuantity());
        detail.setQuantityUnits(dto.getQuantityUnits());
        detail.setBeneficiaryCount(dto.getBeneficiaryCount());

        DemandCdpoDetail saved = detailRepository.save(detail);

        return mapToDTO(saved);
    }

    @Override
    public List<DemandCdpoDetailResponseDTO> createMultipleDetails(List<DemandCdpoDetailRequestDTO> dtos) {
        return dtos.stream()
                .map(this::createDetail)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandCdpoDetailResponseDTO> getDetailsByDemand(Long demandId) {
        return detailRepository.findByDemandId(demandId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DemandCdpoDetailResponseDTO mapToDTO(DemandCdpoDetail detail) {
        DemandCdpoDetailResponseDTO dto = new DemandCdpoDetailResponseDTO();
        dto.setId(detail.getId());
        dto.setDemandId(detail.getDemand().getId());
        dto.setCdpoId(detail.getCdpo().getId());
        dto.setCdpoName(detail.getCdpo().getCdpoName());
        dto.setQuantity(detail.getQuantity());
        dto.setQuantityUnits(detail.getQuantityUnits());
        dto.setBeneficiaryCount(detail.getBeneficiaryCount());
        return dto;
    }
}
