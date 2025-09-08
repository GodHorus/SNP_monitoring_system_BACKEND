package com.example.master.services;

import com.example.master.Dto.CommodityDTO;

import java.util.List;

public interface CommodityService {

//    CommodityDTO createCommodity(CommodityDTO commodityDTO);

    List<CommodityDTO> getAllCommodities();

    CommodityDTO getCommodityById(Long id);

    CommodityDTO updateCommodity(CommodityDTO commodityDTO);

    boolean deleteCommodity(Long id);
}
