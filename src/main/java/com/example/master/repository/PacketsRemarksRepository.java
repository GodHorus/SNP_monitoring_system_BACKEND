package com.example.master.repository;

import com.example.master.model.PacketsRemarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacketsRemarksRepository extends JpaRepository<PacketsRemarks, Long> {
    List<PacketsRemarks> findByAwcDispatch_Id(Long awcDispatchId);
}
