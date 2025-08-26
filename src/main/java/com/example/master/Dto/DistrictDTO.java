package com.example.master.Dto;

import java.util.List;

public class DistrictDTO {
    private Long id;
    private String districtName;
    private List<CdpoDTO> cdpos;

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<CdpoDTO> getCdpos() {
        return cdpos;
    }

    public void setCdpos(List<CdpoDTO> cdpos) {
        this.cdpos = cdpos;
    }
}