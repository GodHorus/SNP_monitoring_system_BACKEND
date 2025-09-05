package com.example.master.repository;

import com.example.master.model.DemandCdpoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandCdpoDetailRepository extends JpaRepository<DemandCdpoDetail, Long> {
    List<DemandCdpoDetail> findByDemandId(Long demandId);
}
