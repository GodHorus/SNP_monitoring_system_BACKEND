package com.example.master.services.impl;

import com.example.master.Dto.DemandDispatchFCIDTO;
import com.example.master.model.DemandDispatchFCI;
import com.example.master.model.Supplier;
import com.example.master.model.Warehouse;
import com.example.master.model.Demand;
import com.example.master.repository.DemandDispatchFCIRepository;
import com.example.master.repository.SupplierRepository;
import com.example.master.repository.WarehouseRepository;
import com.example.master.repository.DemandRepository;
import com.example.master.services.DemandDispatchFCIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandDispatchFCIServiceImpl implements DemandDispatchFCIService {

    @Autowired
    private DemandDispatchFCIRepository demandDispatchFCIRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private DemandRepository demandRepository;

    @Override
    public DemandDispatchFCIDTO createDemandDispatch(DemandDispatchFCIDTO demandDispatchFCIDTO) {
        Supplier supplier = supplierRepository.findById(demandDispatchFCIDTO.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier not found"));
        Warehouse warehouse = warehouseRepository.findById(demandDispatchFCIDTO.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Demand demand = demandRepository.findById(demandDispatchFCIDTO.getDemandId()).orElseThrow(() -> new RuntimeException("Demand not found"));

        DemandDispatchFCI demandDispatchFCI = new DemandDispatchFCI();
        demandDispatchFCI.setDispatchQuantity(demandDispatchFCIDTO.getDispatchQuantity());
        demandDispatchFCI.setRemarks(demandDispatchFCIDTO.getRemarks());
        demandDispatchFCI.setSupplier(supplier);
        demandDispatchFCI.setWarehouse(warehouse);
        demandDispatchFCI.setDemand(demand);

        demandDispatchFCI = demandDispatchFCIRepository.save(demandDispatchFCI);

        return new DemandDispatchFCIDTO(demandDispatchFCI.getId(), demandDispatchFCI.getDispatchQuantity(), demandDispatchFCI.getRemarks(),
                demandDispatchFCI.getSupplier().getId(), demandDispatchFCI.getWarehouse().getId(), demandDispatchFCI.getDemand().getId());
    }
}
