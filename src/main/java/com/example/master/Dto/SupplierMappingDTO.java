package com.example.master.Dto;

import java.util.List;

public class SupplierMappingDTO {
    private Long id;
    private Long supplierId;
    private Long districtId;
    private List<Long> cdpoIds;
    private List<Long> sectorIds;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public List<Long> getCdpoIds() {
        return cdpoIds;
    }

    public void setCdpoIds(List<Long> cdpoIds) {
        this.cdpoIds = cdpoIds;
    }

    public List<Long> getSectorIds() {
        return sectorIds;
    }

    public void setSectorIds(List<Long> sectorIds) {
        this.sectorIds = sectorIds;
    }
}
