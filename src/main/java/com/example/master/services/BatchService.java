package com.example.master.services;

import com.example.master.Dto.BatchDetailDTO;

import java.util.List;

public interface BatchService {
    BatchDetailDTO saveBatch(BatchDetailDTO dto);
    List<BatchDetailDTO> getAllBatches();
}
