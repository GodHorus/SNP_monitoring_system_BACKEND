package com.example.master.services;

import com.example.master.Dto.AWCDispatchDTO;
import com.example.master.model.AWCDispatch;
import java.util.List;

public interface AWCDispatchService {
    AWCDispatch createAWCDispatch(AWCDispatchDTO dto);
    List<AWCDispatch> getAllAWCDispatches();
}
