package com.example.master.Dto;

public class DemandCdpoDetailResponseDTO {
    private Long id;
    private Long demandId;
    private Long cdpoId;
    private String cdpoName;

    private Long districtId;
    private String districtName;
    private Integer quantity;
    private String quantityUnits;
    private Integer beneficiaryCount;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDemandId() { return demandId; }
    public void setDemandId(Long demandId) { this.demandId = demandId; }

    public Long getCdpoId() { return cdpoId; }
    public void setCdpoId(Long cdpoId) { this.cdpoId = cdpoId; }

    public String getCdpoName() { return cdpoName; }
    public void setCdpoName(String cdpoName) { this.cdpoName = cdpoName; }

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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
