package com.example.master.services;

import com.example.master.Dto.DemandDTO;
import com.example.master.Dto.DemandResponseDTO;
import com.example.master.model.Demand;



import java.util.List;
import java.util.Optional;

public interface DemandService {
    Demand createDemand(DemandDTO dto);
    List<DemandResponseDTO> getAllDemands();
    Optional<DemandResponseDTO> getDemandById(Long id);
    void deleteDemand(Long id);
    Demand updateStatus(Long id, String status);

    void updateRejectionReason(Long demandId, String rejectionReason);


    // Additional methods for role-specific queries
    List<DemandResponseDTO> getDemandsByStatus(String status);
    List<DemandResponseDTO> getPendingDemandsForFCI();
    List<DemandResponseDTO> getAcceptedDemandsForSupplier();
    List<DemandResponseDTO> getManufacturedDemandsForCDPO();
    List<DemandResponseDTO> getDispatchedDemandsForAWC();
}
