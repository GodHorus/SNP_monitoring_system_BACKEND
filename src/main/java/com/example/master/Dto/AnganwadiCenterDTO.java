package com.example.master.Dto;

public class AnganwadiCenterDTO {

    private Long id;
    private String centerName;
    private String centerAddress;
    private String status;

    private Long supervisorId;

    private Long cdpoId;

    private Long districtId;


    public AnganwadiCenterDTO() {}

//    public AnganwadiCenterDTO(Long id, String centerName, String centerAddress, String status,
//                              Long supervisorId, Long cdpoId, Long districtId) {
//        this.id = id;
//        this.centerName = centerName;
//        this.centerAddress = centerAddress;
//        this.status = status;
//        this.supervisorId = supervisorId;
//        this.cdpoId = cdpoId;
//        this.districtId = districtId;
//    }

    public AnganwadiCenterDTO(Long id, String centerName, String centerAddress, String status) {
        this.id = id;
        this.centerName = centerName;
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.status = status;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCenterName() { return centerName; }
    public void setCenterName(String centerName) { this.centerName = centerName; }

    public String getCenterAddress() { return centerAddress; }
    public void setCenterAddress(String centerAddress) { this.centerAddress = centerAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getSupervisorId() { return supervisorId; }
    public void setSupervisorId(Long supervisorId) { this.supervisorId = supervisorId; }


    public Long getCdpoId() { return cdpoId; }
    public void setCdpoId(Long cdpoId) { this.cdpoId = cdpoId; }


    public Long getDistrictId() { return districtId; }
    public void setDistrictId(Long districtId) { this.districtId = districtId; }

}
