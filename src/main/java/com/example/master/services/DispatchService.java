package com.example.master.services;
import com.example.master.Dto.DispatchDetailDTO;

import java.util.List;

public interface DispatchService {
//    DispatchDetailDTO saveDispatch(DispatchDetailDTO dto);

    List<DispatchDetailDTO> saveDispatchDetails(List<DispatchDetailDTO> dtoList);
//    List<DispatchDetailDTO> getAllDispatches();
    List<DispatchDetailDTO> getAllDispatchDetails();

}