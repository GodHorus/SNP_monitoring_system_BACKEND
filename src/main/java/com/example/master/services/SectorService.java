package com.example.master.services;

import com.example.master.Dto.SectorDTO;

import java.util.List;

public interface SectorService {
    SectorDTO createSector(SectorDTO sectorDTO);
    SectorDTO updateSector(Long id, SectorDTO sectorDTO);
    void deleteSector(Long id);
    SectorDTO getSectorById(Long id);
    List<SectorDTO> getAllSectors();

    List<SectorDTO> getAllSectorsByCdpo(Long cdpoId);

    SectorDTO getSectorWithAnganwadies(Long sectorId);

}
