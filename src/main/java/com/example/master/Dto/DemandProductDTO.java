package com.example.master.Dto;

import java.util.List;

public class DemandProductDTO {
    private Long id;
    private String type;
    private List<ProductCommodityQuantityDTO> productQuantities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductCommodityQuantityDTO> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(List<ProductCommodityQuantityDTO> productQuantities) {
        this.productQuantities = productQuantities;
    }
}
