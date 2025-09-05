package com.example.master.Dto;

import java.util.Map;

public class ProductQuantityResponse {
    private Long demandProductId;
    private String productType;
    private Map<String, Double> commodityQuantities;
    // key: commodityName, value: quantity


    public Long getDemandProductId() {
        return demandProductId;
    }

    public void setDemandProductId(Long demandProductId) {
        this.demandProductId = demandProductId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Map<String, Double> getCommodityQuantities() {
        return commodityQuantities;
    }

    public void setCommodityQuantities(Map<String, Double> commodityQuantities) {
        this.commodityQuantities = commodityQuantities;
    }
}
