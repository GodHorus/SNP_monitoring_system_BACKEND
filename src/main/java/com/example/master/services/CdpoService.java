package com.example.master.services;

import com.example.master.Dto.CdpoDTO;
import java.util.List;

public interface CdpoService {
    List<CdpoDTO> getAllCdpos();
    CdpoDTO getCdpoById(Long id);
    CdpoDTO createCdpo(CdpoDTO cdpoDTO);
    CdpoDTO updateCdpo(Long id, CdpoDTO cdpoDTO);
    void deleteCdpo(Long id);
}
