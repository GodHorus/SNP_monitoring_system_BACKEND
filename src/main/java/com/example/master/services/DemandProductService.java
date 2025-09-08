package com.example.master.services;

import com.example.master.model.DemandProduct;

import java.util.List;

public interface DemandProductService {

    DemandProduct createDemandProduct(DemandProduct demandProduct);

    List<DemandProduct> getAllDemandProducts();

    DemandProduct getDemandProductById(Long id);

    DemandProduct updateDemandProduct(DemandProduct demandProduct);

    boolean deleteDemandProduct(Long id);
}
