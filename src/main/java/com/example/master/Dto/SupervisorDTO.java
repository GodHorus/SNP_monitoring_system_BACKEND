package com.example.master.Dto;

import com.example.master.model.Supervisor;

import java.util.List;
import java.util.stream.Collectors;

public class SupervisorDTO {
    private Long id;
    private String name;
    private List<AnganwadiCenterDTO> anganwadiCenters;

    // Constructor to map from Supervisor entity
//    public SupervisorDTO(Supervisor supervisor) {
//        this.id = supervisor.getId();
//        this.name = supervisor.getName();
//
//        if (supervisor.getAnganwadiCenters() != null) {
//            this.anganwadiCenters = supervisor.getAnganwadiCenters().stream()
//                    .map(AnganwadiCenterDTO::new) // AnganwadiCenterDTO should have a constructor taking AnganwadiCenter entity
//                    .collect(Collectors.toList());
//        }
//    }

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
