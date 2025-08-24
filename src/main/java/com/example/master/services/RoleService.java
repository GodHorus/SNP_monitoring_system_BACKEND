package com.example.master.services;

import com.example.master.Dto.RoleDTO;
import java.util.List;

public interface RoleService {
    List<RoleDTO> getAll();
    RoleDTO getById(Long id);
    RoleDTO create(RoleDTO dto);
    RoleDTO update(Long id, RoleDTO dto);
    void delete(Long id);
}
