package com.example.master.services;

import com.example.master.Dto.AWCDispatchDTO;
import java.util.List;

public interface AWCDispatchService {
    AWCDispatchDTO createAWCDispatch(AWCDispatchDTO dto);
    List<AWCDispatchDTO> getAllAWCDispatches();
}
