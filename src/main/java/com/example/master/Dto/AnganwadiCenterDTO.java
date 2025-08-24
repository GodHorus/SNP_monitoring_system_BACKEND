package com.example.master.Dto;


public class AnganwadiCenterDTO {


    private Long id;


    private String centerId;


    private String centerName;

    private String centerAddress;


    private String status; // "active" | "inactive"

    // Related names (simple mapping for your UI)
    private String supervisorName;
    private String districtName;
    private String blockName;

    public AnganwadiCenterDTO() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCenterId() { return centerId; }
    public void setCenterId(String centerId) { this.centerId = centerId; }

    public String getCenterName() { return centerName; }
    public void setCenterName(String centerName) { this.centerName = centerName; }

    public String getCenterAddress() { return centerAddress; }
    public void setCenterAddress(String centerAddress) { this.centerAddress = centerAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSupervisorName() { return supervisorName; }
    public void setSupervisorName(String supervisorName) { this.supervisorName = supervisorName; }

    public String getDistrictName() { return districtName; }
    public void setDistrictName(String districtName) { this.districtName = districtName; }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }
}
