package com.example.master.Dto;

import java.util.List;

public class CdpoDTO {
    private Long id;
    private String cdpoName;
    private List<SupervisorDTO> supervisors;

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

    public List<SupervisorDTO> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<SupervisorDTO> supervisors) {
        this.supervisors = supervisors;
    }
}
