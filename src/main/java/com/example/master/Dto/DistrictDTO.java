package com.example.master.Dto;

import com.example.master.model.District;

import java.util.List;
import java.util.stream.Collectors;

public class DistrictDTO {
    private Long id;
    private String districtName;
    private List<CdpoDTO> cdpos;

//    public DistrictDTO(District district) {
//        this.id = district.getId();
//        this.districtName = district.getDistrictName();
//
//        if (district.getCdpos() != null) {
//            this.cdpos = district.getCdpos().stream()
//                    .map(CdpoDTO::new)  // CdpoDTO should have a constructor that takes Cdpo
//                    .collect(Collectors.toList());
//        }
//    }

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