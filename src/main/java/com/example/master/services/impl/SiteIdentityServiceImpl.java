package com.example.master.services.impl;

import com.example.master.Dto.SiteIdentityDTO;
import com.example.master.exception.NotFoundException;
import com.example.master.model.SiteIdentity;
import com.example.master.repository.SiteIdentityRepository;
import com.example.master.services.SiteIdentityService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteIdentityServiceImpl implements SiteIdentityService {

    private final SiteIdentityRepository repository;

    public SiteIdentityServiceImpl(SiteIdentityRepository repository) {
        this.repository = repository;
    }

    private SiteIdentityDTO convertToDTO(SiteIdentity entity) {
        SiteIdentityDTO dto = new SiteIdentityDTO();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setHeaderName(entity.getHeaderName());
        dto.setSiteLogo(entity.getSiteLogo());
        dto.setLoginSiteLogo(entity.getLoginSiteLogo());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    private SiteIdentity convertToEntity(SiteIdentityDTO dto) {
        SiteIdentity entity = new SiteIdentity();
        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setHeaderName(dto.getHeaderName());
        entity.setSiteLogo(dto.getSiteLogo());
        entity.setLoginSiteLogo(dto.getLoginSiteLogo());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    @Override
    public SiteIdentityDTO getSiteIdentity(Long id) {
        return repository.findById(id).map(this::convertToDTO)
                .orElseThrow(() -> new NotFoundException("Site identity not found with id " + id));
    }

    @Override
    public List<SiteIdentityDTO> getAllSiteIdentities() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SiteIdentityDTO createSiteIdentity(SiteIdentityDTO dto) {
        SiteIdentity entity = convertToEntity(dto);
        return convertToDTO(repository.save(entity));
    }

    @Override
    public SiteIdentityDTO updateSiteIdentity(Long id, SiteIdentityDTO dto) {
        SiteIdentity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Site identity not found with id " + id));
        entity.setAddress(dto.getAddress());
        entity.setHeaderName(dto.getHeaderName());
        entity.setSiteLogo(dto.getSiteLogo());
        entity.setLoginSiteLogo(dto.getLoginSiteLogo());
        entity.setStatus(dto.getStatus());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void deleteSiteIdentity(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Site identity not found with id " + id);
        }
        repository.deleteById(id);
    }
}
