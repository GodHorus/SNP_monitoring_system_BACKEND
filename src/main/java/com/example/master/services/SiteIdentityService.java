package com.example.master.services;

import com.example.master.Dto.SiteIdentityDTO;
import java.util.List;

public interface SiteIdentityService {
    SiteIdentityDTO getSiteIdentity(Long id);
    List<SiteIdentityDTO> getAllSiteIdentities();
    SiteIdentityDTO createSiteIdentity(SiteIdentityDTO dto);
    SiteIdentityDTO updateSiteIdentity(Long id, SiteIdentityDTO dto);
    void deleteSiteIdentity(Long id);
}
