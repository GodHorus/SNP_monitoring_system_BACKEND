package com.example.master.services;

import com.example.master.Dto.AWCAcceptDemandDTO;

import java.util.List;

public interface AWCAcceptDemandService {

    AWCAcceptDemandDTO createAcceptDemand(AWCAcceptDemandDTO dto);

    List<AWCAcceptDemandDTO> createAcceptDemands(List<AWCAcceptDemandDTO> dtos);

    AWCAcceptDemandDTO getAcceptDemandById(Long id);

    List<AWCAcceptDemandDTO> getAllAcceptDemands();

    AWCAcceptDemandDTO updateAcceptDemand(Long id, AWCAcceptDemandDTO dto);

    void deleteAcceptDemand(Long id);
}
