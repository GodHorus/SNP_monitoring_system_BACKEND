package com.example.master.services.impl;

import com.example.master.Dto.CommodityDTO;
import com.example.master.model.Commodity;
import com.example.master.repository.CommodityRepository;
import com.example.master.services.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

//    @Override
//    public CommodityDTO createCommodity(CommodityDTO commodityDTO) {
//        Commodity commodity = new Commodity(commodityDTO.getName());
//        Commodity savedCommodity = commodityRepository.save(commodity);
//        return new CommodityDTO(savedCommodity.getId(), savedCommodity.getName());
//    }

    @Override
    public List<CommodityDTO> getAllCommodities() {
        List<Commodity> commodities = commodityRepository.findAll();
        return commodities.stream()
                .map(commodity -> new CommodityDTO(commodity.getId(), commodity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CommodityDTO getCommodityById(Long id) {
        Optional<Commodity> commodity = commodityRepository.findById(id);
        return commodity.map(value -> new CommodityDTO(value.getId(), value.getName()))
                .orElse(null);
    }

    @Override
    public CommodityDTO updateCommodity(CommodityDTO commodityDTO) {
        Optional<Commodity> existingCommodity = commodityRepository.findById(commodityDTO.getId());
        if (existingCommodity.isPresent()) {
            Commodity commodity = existingCommodity.get();
            commodity.setName(commodityDTO.getName());
            Commodity updatedCommodity = commodityRepository.save(commodity);
            return new CommodityDTO(updatedCommodity.getId(), updatedCommodity.getName());
        }
        return null; // Or throw an exception
    }

    @Override
    public boolean deleteCommodity(Long id) {
        if (commodityRepository.existsById(id)) {
            commodityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
