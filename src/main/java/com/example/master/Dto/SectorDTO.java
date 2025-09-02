package com.example.master.Dto;

import java.util.List;

public class SectorDTO {
    private Long id;
    private Long cdpoId;
    private String name;
    private String status;
    private List<AnganwadiCenterDTO> anganwadiCenters;

    public SectorDTO() {}

    public SectorDTO(Long id, Long cdpoId, String name, String status, List<AnganwadiCenterDTO> anganwadiCenters) {
        this.id = id;
        this.cdpoId = cdpoId;
        this.name = name;
        this.status = status;
        this.anganwadiCenters = anganwadiCenters;
    }

    // Getters & Setters
    public List<AnganwadiCenterDTO> getAnganwadiCenters() {
        return anganwadiCenters;
    }

    public void setAnganwadiCenters(List<AnganwadiCenterDTO> anganwadiCenters) {
        this.anganwadiCenters = anganwadiCenters;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCdpoId() { return cdpoId; }
    public void setCdpoId(Long cdpoId) { this.cdpoId = cdpoId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
