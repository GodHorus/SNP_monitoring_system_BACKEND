package com.example.master.Dto;

import com.example.master.model.Cdpo;

import java.util.List;
import java.util.stream.Collectors;

public class CdpoDTO {
    private Long id;
    private String cdpoName;
//    private List<SupervisorDTO> supervisors;

    private List<SectorDTO> sectors;

    // Constructor to map from Cdpo entity
//    public CdpoDTO(Cdpo cdpo) {
//        this.id = cdpo.getId();
//        this.cdpoName = cdpo.getCdpoName();
//
//        if (cdpo.getSupervisors() != null) {
//            this.supervisors = cdpo.getSupervisors().stream()
//                    .map(SupervisorDTO::new) // SupervisorDTO should have a constructor taking Supervisor entity
//                    .collect(Collectors.toList());
//        }
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCdpoName() {
        return cdpoName;
    }

    public void setCdpoName(String cdpoName) {
        this.cdpoName = cdpoName;
    }

//    public List<SupervisorDTO> getSupervisors() {
//        return supervisors;
//    }
//
//    public void setSupervisors(List<SupervisorDTO> supervisors) {
//        this.supervisors = supervisors;
//    }


    public List<SectorDTO> getSectors() {
        return sectors;
    }

    public void setSectors(List<SectorDTO> sectors) {
        this.sectors = sectors;
    }
}
