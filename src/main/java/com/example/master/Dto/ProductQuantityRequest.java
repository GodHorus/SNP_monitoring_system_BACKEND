package com.example.master.Dto;

import java.util.Map;

public class ProductQuantityRequest {
    private Long demandProductId;
    private Map<Long, Double> commodityQuantities;
    // key: commodityId, value: quantity


    public Long getDemandProductId() {
        return demandProductId;
    }

    public void setDemandProductId(Long demandProductId) {
        this.demandProductId = demandProductId;
    }

    public Map<Long, Double> getCommodityQuantities() {
        return commodityQuantities;
    }

    public void setCommodityQuantities(Map<Long, Double> commodityQuantities) {
        this.commodityQuantities = commodityQuantities;
    }
}
