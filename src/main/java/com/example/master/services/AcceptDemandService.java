package com.example.master.services;

import com.example.master.Dto.AcceptDemandDTO;
import com.example.master.model.AcceptDemand;
import java.util.List;
import java.util.Optional;

public interface AcceptDemandService {
//    AcceptDemand createAcceptDemand(AcceptDemandDTO dto);

    AcceptDemandDTO create(AcceptDemandDTO dto);
    List<AcceptDemandDTO> getByDemandId(Long demandId);
}
