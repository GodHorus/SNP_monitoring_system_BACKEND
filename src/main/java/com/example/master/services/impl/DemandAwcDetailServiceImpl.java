package com.example.master.services.impl;

import com.example.master.Dto.DemandAwcDetailDTO;
import com.example.master.model.AnganwadiCenter;
import com.example.master.model.Demand;
import com.example.master.model.DemandAwcDetail;
import com.example.master.repository.AnganwadiCenterRepository;
import com.example.master.repository.DemandAwcDetailRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.services.DemandAwcDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemandAwcDetailServiceImpl implements DemandAwcDetailService {

    private final DemandAwcDetailRepository demandAwcDetailRepository;
    private final DemandRepository demandRepository;
    private final AnganwadiCenterRepository anganwadiCenterRepository;

    public DemandAwcDetailServiceImpl(DemandAwcDetailRepository demandAwcDetailRepository,
                                      DemandRepository demandRepository,
                                      AnganwadiCenterRepository anganwadiCenterRepository) {
        this.demandAwcDetailRepository = demandAwcDetailRepository;
        this.demandRepository = demandRepository;
        this.anganwadiCenterRepository = anganwadiCenterRepository;
    }

    @Override
    public List<DemandAwcDetail> getAll() {
        return demandAwcDetailRepository.findAll();
    }


    @Override
    @Transactional
    public DemandAwcDetail createDemandAwcDetail(Long demandId, DemandAwcDetailDTO dto) {
        Demand demand = demandRepository.findById(demandId)
                .orElseThrow(() -> new RuntimeException("Demand not found with ID: " + demandId));

        AnganwadiCenter awc = anganwadiCenterRepository.findById(dto.getAwcId())
                .orElseThrow(() -> new RuntimeException("AWC not found with ID: " + dto.getAwcId()));

        DemandAwcDetail detail = new DemandAwcDetail();
        detail.setDemand(demand);
        detail.setAnganwadi(awc);
        detail.setHcmNumber(dto.getHcmNumber());
        detail.setHcmUnit(dto.getHcmUnit());
//        detail.setThrNumber(dto.ge());
//        detail.setThrUnit(dto.getThrUnit());

        return demandAwcDetailRepository.save(detail);
    }

    @Override
    public List<DemandAwcDetail> getDemandAwcDetailsByDemand(Long demandId) {
        return demandAwcDetailRepository.findByDemandId(demandId);
    }
}
