package com.example.master.services.impl;

import com.example.master.Dto.RoleDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.Role;
import com.example.master.repository.RoleRepository;
import com.example.master.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repo;

    public RoleServiceImpl(RoleRepository repo) { this.repo = repo; }

    private RoleDTO mapToDTO(Role r) {
        return new RoleDTO(r.getId(), r.getName());
    }

    private Role mapToEntity(RoleDTO dto) {
        Role r = new Role();
        r.setId(dto.getId());
        r.setName(dto.getName());
        return r;
    }

    @Override
    public List<RoleDTO> getAll() {
        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getById(Long id) {
        Role r = repo.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        return mapToDTO(r);
    }

    @Override
    public RoleDTO create(RoleDTO dto) {
        Role role = mapToEntity(dto);
        return mapToDTO(repo.save(role));
    }

    @Override
    public RoleDTO update(Long id, RoleDTO dto) {
        Role r = repo.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        r.setName(dto.getName());
        return mapToDTO(repo.save(r));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Role not found");
        repo.deleteById(id);
    }
}
