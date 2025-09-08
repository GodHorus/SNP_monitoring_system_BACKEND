package com.example.master.services.impl;

import com.example.master.model.DemandProduct;
import com.example.master.repository.DemandProductRepository;
import com.example.master.services.DemandProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandProductServiceImpl implements DemandProductService {

    @Autowired
    private DemandProductRepository demandProductRepository;

    @Override
    public DemandProduct createDemandProduct(DemandProduct demandProduct) {
        return demandProductRepository.save(demandProduct);
    }

    @Override
    public List<DemandProduct> getAllDemandProducts() {
        return demandProductRepository.findAll();
    }

    @Override
    public DemandProduct getDemandProductById(Long id) {
        Optional<DemandProduct> demandProduct = demandProductRepository.findById(id);
        return demandProduct.orElse(null);
    }

    @Override
    public DemandProduct updateDemandProduct(DemandProduct demandProduct) {
        return demandProductRepository.save(demandProduct);
    }

    @Override
    public boolean deleteDemandProduct(Long id) {
        if (demandProductRepository.existsById(id)) {
            demandProductRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
