package com.example.master.services;

import com.example.master.Dto.AnganwadiCenterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnganwadiCenterService {

    Page<AnganwadiCenterDTO> list(Pageable pageable);

    AnganwadiCenterDTO get(Long id);

    AnganwadiCenterDTO create(AnganwadiCenterDTO dto);

    AnganwadiCenterDTO update(Long id, AnganwadiCenterDTO dto);

    void delete(Long id);
}
