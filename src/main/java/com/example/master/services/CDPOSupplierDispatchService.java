package com.example.master.services;

import com.example.master.Dto.CDPOSupplierDispatchDTO;
import java.util.List;

public interface CDPOSupplierDispatchService {
//    CDPOSupplierDispatchDTO createDispatch(CDPOSupplierDispatchDTO dto);
//    CDPOSupplierDispatchDTO getDispatchById(Long id);
//    List<CDPOSupplierDispatchDTO> getAllDispatches();
//    void deleteDispatch(Long id);
//    List<CDPOSupplierDispatchDTO> createDispatches(List<CDPOSupplierDispatchDTO> dtos);

    CDPOSupplierDispatchDTO create(CDPOSupplierDispatchDTO dto);
    List<CDPOSupplierDispatchDTO> getByDemandId(Long demandId);

}
