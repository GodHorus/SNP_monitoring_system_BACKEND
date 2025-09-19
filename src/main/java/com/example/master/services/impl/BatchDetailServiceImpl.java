// BatchDetailServiceImpl.java
package com.example.master.services.impl;

import com.example.master.model.BatchDetail;
import com.example.master.repository.BatchDetailRepository;
import com.example.master.services.BatchDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BatchDetailServiceImpl implements BatchDetailService {

    private final BatchDetailRepository repo;

    public BatchDetailServiceImpl(BatchDetailRepository repo) { this.repo = repo; }

    @Override
    public BatchDetail createBatch(BatchDetail batch) {
        // generate batchNumber like B-<n> (global increasing)
        Long next = 1L;
        var last = repo.findTopByOrderByIdDesc();
        if (last.isPresent()) next = last.get().getId() + 1;
        String batchNumber = "B-" + next;
        batch.setBatchNumber(batchNumber);
        return repo.save(batch);
    }

    @Override
    public List<BatchDetail> findByDemandId(Long demandId) {
        return repo.findByDemandId(demandId);
    }
}
