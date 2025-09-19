// PackagingDetailServiceImpl.java
package com.example.master.services.impl;

import com.example.master.model.PackagingDetail;
import com.example.master.repository.PackagingDetailRepository;
import com.example.master.services.PackagingDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackagingDetailServiceImpl implements PackagingDetailService {

    private final PackagingDetailRepository repo;

    public PackagingDetailServiceImpl(PackagingDetailRepository repo){ this.repo = repo; }

    @Override
    public PackagingDetail createPackaging(PackagingDetail p) {
        return repo.save(p);
    }

    @Override
    public List<PackagingDetail> createPackagings(List<PackagingDetail> packagings) {
        return repo.saveAll(packagings);
    }


    @Override
    public List<PackagingDetail> findByDemandId(Long demandId) {
        return repo.findByDemandId(demandId);
    }
}
