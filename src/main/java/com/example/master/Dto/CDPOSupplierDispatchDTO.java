package com.example.master.Dto;

public class CDPOSupplierDispatchDTO {
    private Long id;
    private Integer dispatchPackets;
    private String remarks;
    private Long acceptDemandId;

    private String sublotNo;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDispatchPackets() {
        return dispatchPackets;
    }
    public void setDispatchPackets(Integer dispatchPackets) {
        this.dispatchPackets = dispatchPackets;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getAcceptDemandId() {
        return acceptDemandId;
    }
    public void setAcceptDemandId(Long acceptDemandId) {
        this.acceptDemandId = acceptDemandId;
    }

    public String getSublotNo() {
        return sublotNo;
    }

    public void setSublotNo(String sublotNo) {
        this.sublotNo = sublotNo;
    }
}
