package com.example.master.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class AWCDispatchDTO {
    private Long id;
    private String sublotNo;
    private Integer benficiaryCount;
    private Integer distributedPackets;
    private LocalDateTime createdAt;

    private Long demandId;
    private String demandName; // From Demand entity

    private Long anganwadiId;
    private String anganwadiName;
//    private List<Long> anganwadiCenters;
//    private List<AnganwadiCenterDTO> anganwadiCentersData;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSublotNo() {
        return sublotNo;
    }

    public void setSublotNo(String sublotNo) {
        this.sublotNo = sublotNo;
    }

    public Integer getBenficiaryCount() {
        return benficiaryCount;
    }

    public void setBenficiaryCount(Integer benficiaryCount) {
        this.benficiaryCount = benficiaryCount;
    }

    public Integer getDistributedPackets() {
        return distributedPackets;
    }

    public void setDistributedPackets(Integer distributedPackets) {
        this.distributedPackets = distributedPackets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

//    public List<Long> getAnganwadiCenters() {
//        return anganwadiCenters;
//    }
//
//    public void setAnganwadiCenters(List<Long> anganwadiCenters) {
//        this.anganwadiCenters = anganwadiCenters;
//    }
//
//    public List<AnganwadiCenterDTO> getAnganwadiCentersData() {
//        return anganwadiCentersData;
//    }
//
//    public void setAnganwadiCentersData(List<AnganwadiCenterDTO> anganwadiCentersData) {
//        this.anganwadiCentersData = anganwadiCentersData;
//    }


    public Long getAnganwadiId() {
        return anganwadiId;
    }

    public void setAnganwadiId(Long anganwadiId) {
        this.anganwadiId = anganwadiId;
    }

    public String getAnganwadiName() {
        return anganwadiName;
    }

    public void setAnganwadiName(String anganwadiName) {
        this.anganwadiName = anganwadiName;
    }
}
