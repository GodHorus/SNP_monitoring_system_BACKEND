package com.example.master.services;

import com.example.master.Dto.DemandDTO;
import com.example.master.model.Demand;

import java.util.List;

public interface DemandService {
    Demand createDemand(DemandDTO dto);
    List<Demand> getAllDemands();
    Demand getDemandById(Long id);
    void deleteDemand(Long id);
    void updateStatus(Long id, String status);
}
