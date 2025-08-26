package com.example.master.services;

import com.example.master.Dto.PackagingDetailDTO;

import java.util.List;

public interface PackagingService {
    PackagingDetailDTO savePackaging(PackagingDetailDTO dto);
    List<PackagingDetailDTO> getAllPackaging();
}
