package com.example.master.Dto;

import com.example.master.model.DispatchDetail;

public class AcceptDemandDTO {

    private Long id;

    private Long dispatchId;   // mapped from DispatchDetail
    private Integer receivedPackets;
    private String remarks;

    private String qrCode;

//    private DispatchDetail dispatch;

//    public DispatchDetail getDispatch() {
//        return dispatch;
//    }
//
//    public void setDispatch(DispatchDetail dispatch) {
//        this.dispatch = dispatch;
//    }
    // Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDispatchId() { return dispatchId; }
    public void setDispatchId(Long dispatchId) { this.dispatchId = dispatchId; }

    public Integer getReceivedPackets() { return receivedPackets; }
    public void setReceivedPackets(Integer receivedPackets) { this.receivedPackets = receivedPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
