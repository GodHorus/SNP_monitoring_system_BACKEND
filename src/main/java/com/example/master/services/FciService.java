package com.example.master.services;

import com.example.master.Dto.FciDTO;
import java.util.List;

public interface FciService {
    List<FciDTO> getAllFci();
    FciDTO getFciById(Long id);
    FciDTO createFci(FciDTO fciDTO);
    FciDTO updateFci(Long id, FciDTO fciDTO);
    void deleteFci(Long id);
}
