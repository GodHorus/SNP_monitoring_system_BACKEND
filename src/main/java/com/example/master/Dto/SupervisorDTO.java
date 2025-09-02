package com.example.master.Dto;

import com.example.master.model.Supervisor;

import java.util.List;
import java.util.stream.Collectors;

public class SupervisorDTO {
    private Long id;
    private String name;
    private List<AnganwadiCenterDTO> anganwadiCenters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnganwadiCenterDTO> getAnganwadiCenters() {
        return anganwadiCenters;
    }

    public void setAnganwadiCenters(List<AnganwadiCenterDTO> anganwadiCenters) {
        this.anganwadiCenters = anganwadiCenters;
    }
}
