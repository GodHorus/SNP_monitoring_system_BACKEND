package com.example.master.repository;

import com.example.master.model.DemandDispatchFCI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandDispatchFCIRepository extends JpaRepository<DemandDispatchFCI, Long> {
}
