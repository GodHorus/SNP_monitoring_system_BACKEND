package com.example.master.services;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.model.AcceptDemand;
import java.util.List;
import java.util.Optional;

public interface AcceptDemandService {
//    AcceptDemand createAcceptDemand(AcceptDemandDTO dto);

    List<AcceptDemand> createAcceptDemands(List<AcceptDemandDTO> dtos);
    List<AcceptDemandDTO> getAllAcceptDemands();

    Optional<AcceptDemandDTO> getAcceptDemandByDemandId(Long demandId);
}
