package com.example.master.services;

import com.example.master.Dto.DemandAwcDetailDTO;
import com.example.master.model.DemandAwcDetail;

import java.util.List;

public interface DemandAwcDetailService {
    DemandAwcDetail createDemandAwcDetail(Long demandId, DemandAwcDetailDTO dto);
    List<DemandAwcDetail> getDemandAwcDetailsByDemand(Long demandId);

    List<DemandAwcDetail> getAll();

}
