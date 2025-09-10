package com.example.master.Dto;

public class DemandCdpoDetailRequestDTO {
    private Long demandId;
    private Long cdpoId;
    private Long districtId;
    private Integer quantity;
    private String quantityUnits;
    private Integer beneficiaryCount;

    // Getters and Setters
    public Long getDemandId() { return demandId; }
    public void setDemandId(Long demandId) { this.demandId = demandId; }

    public Long getCdpoId() { return cdpoId; }
    public void setCdpoId(Long cdpoId) { this.cdpoId = cdpoId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getQuantityUnits() { return quantityUnits; }
    public void setQuantityUnits(String quantityUnits) { this.quantityUnits = quantityUnits; }

    public Integer getBeneficiaryCount() { return beneficiaryCount; }
    public void setBeneficiaryCount(Integer beneficiaryCount) { this.beneficiaryCount = beneficiaryCount; }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
}
