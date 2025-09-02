package com.example.master.services;

import com.example.master.Dto.AWCDistributeDTO;

import java.util.List;

public interface AWCDistributeService {

    AWCDistributeDTO createAWCDistribute(AWCDistributeDTO dto);
//List<AWCDistributeDTO> createAWCDistributes(List<AWCDistributeDTO> dtos);

    AWCDistributeDTO getAWCDistributeById(Long id);

    List<AWCDistributeDTO> getAllAWCDistributes();

    AWCDistributeDTO updateAWCDistribute(Long id, AWCDistributeDTO dto);

    void deleteAWCDistribute(Long id);
}
