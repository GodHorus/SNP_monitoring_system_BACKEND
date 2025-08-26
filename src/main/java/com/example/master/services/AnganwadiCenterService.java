package com.example.master.services;

import com.example.master.Dto.AnganwadiCenterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnganwadiCenterService {

    AnganwadiCenterDTO create(AnganwadiCenterDTO dto);

    AnganwadiCenterDTO update(Long id, AnganwadiCenterDTO dto);

    void delete(Long id);

    AnganwadiCenterDTO get(Long id);   // renamed to match controller

    Page<AnganwadiCenterDTO> list(Pageable pageable); //  paging support
}
