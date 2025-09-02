package com.example.master.services;

import com.example.master.Dto.PackagingDetailDTO;

import java.util.List;

public interface PackagingService {
    List<PackagingDetailDTO> savePackaging(List<PackagingDetailDTO> dto);
    List<PackagingDetailDTO> getAllPackaging();
}
