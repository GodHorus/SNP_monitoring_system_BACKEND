package com.example.master.services;

import com.example.master.Dto.AWCDispatchDTO;

import java.util.List;

public interface AWCDispatchService {
    //    AWCDispatchDTO createAWCDispatch(AWCDispatchDTO dto);
//    List<AWCDispatchDTO> getAllAWCDispatches();
    AWCDispatchDTO createDispatch(AWCDispatchDTO dto);

    AWCDispatchDTO getDispatchById(Long id);

    List<AWCDispatchDTO> getAllDispatches();

    AWCDispatchDTO updateDispatch(Long id, AWCDispatchDTO dto);

    void deleteDispatch(Long id);

    List<AWCDispatchDTO> createDispatches(List<AWCDispatchDTO> dtos);
}
