// PackagingDetailRepository.java
package com.example.master.repository;

import com.example.master.model.PackagingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PackagingDetailRepository extends JpaRepository<PackagingDetail, Long> {
    List<PackagingDetail> findByDemandId(Long demandId);
}
