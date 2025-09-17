package com.example.master.services;

import com.example.master.Dto.PacketsRemarksDTO;

import java.util.List;

public interface PacketsRemarksService {
    PacketsRemarksDTO create(PacketsRemarksDTO dto);
    PacketsRemarksDTO update(Long id, PacketsRemarksDTO dto);
    void delete(Long id);
    PacketsRemarksDTO getById(Long id);
    List<PacketsRemarksDTO> getByAwcDispatchId(Long awcDispatchId);
    List<PacketsRemarksDTO> getAll();
}
