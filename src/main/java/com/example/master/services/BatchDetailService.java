// BatchDetailService.java
package com.example.master.services;

import com.example.master.model.BatchDetail;
import java.util.List;

public interface BatchDetailService {
    BatchDetail createBatch(BatchDetail batch);
    List<BatchDetail> findByDemandId(Long demandId);
}
