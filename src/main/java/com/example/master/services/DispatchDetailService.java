// DispatchDetailService.java
package com.example.master.services;

import com.example.master.model.DispatchDetail;
import java.util.List;

public interface DispatchDetailService {
    DispatchDetail createDispatch(DispatchDetail d);

    List<DispatchDetail> createDispatches(List<DispatchDetail> dispatches);
    List<DispatchDetail> findByDemandId(Long demandId);


}
