// PackagingDetailService.java
package com.example.master.services;

import com.example.master.model.PackagingDetail;
import java.util.List;

public interface PackagingDetailService {
    PackagingDetail createPackaging(PackagingDetail p);

    List<PackagingDetail> createPackagings(List<PackagingDetail> packagings);
    List<PackagingDetail> findByDemandId(Long demandId);
}
