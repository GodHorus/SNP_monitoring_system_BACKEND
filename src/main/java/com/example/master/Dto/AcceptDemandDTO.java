package com.example.master.Dto;

public class AcceptDemandDTO {

    private Long dispatchId;   // mapped from DispatchDetail
    private Integer receivedPackets;
    private String remarks;

    // Getters & Setters
    public Long getDispatchId() { return dispatchId; }
    public void setDispatchId(Long dispatchId) { this.dispatchId = dispatchId; }

    public Integer getReceivedPackets() { return receivedPackets; }
    public void setReceivedPackets(Integer receivedPackets) { this.receivedPackets = receivedPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
