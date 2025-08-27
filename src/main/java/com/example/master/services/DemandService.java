package com.example.master.services;

import com.example.master.Dto.DemandDTO;
import com.example.master.Dto.DemandResponseDTO;
import com.example.master.model.Demand;



import java.util.List;

public interface DemandService {
    Demand createDemand(DemandDTO dto);
    List<DemandResponseDTO> getAllDemands();
    Demand getDemandById(Long id);
    void deleteDemand(Long id);
    Demand updateStatus(Long id, String status);

    // Additional methods for role-specific queries
    List<DemandResponseDTO> getDemandsByStatus(String status);
    List<DemandResponseDTO> getPendingDemandsForFCI();
    List<DemandResponseDTO> getAcceptedDemandsForSupplier();
    List<DemandResponseDTO> getManufacturedDemandsForCDPO();
    List<DemandResponseDTO> getDispatchedDemandsForAWC();
}
