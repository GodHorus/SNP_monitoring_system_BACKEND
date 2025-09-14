package com.example.master.Dto;

import com.example.master.model.DispatchDetail;

import java.util.List;

public class AcceptDemandDTO {

    private Long id;

    private Long demandId;      // mapped from DispatchDetail
    private Integer receivedPackets;
    private String remarks;

    private String qrCode;

    private List<CDPOSupplierDispatchDTO> cdpoSupplierDispatches;

//    private DispatchDetail dispatch;

//    public DispatchDetail getDispatch() {
//        return dispatch;
//    }
//
//    public void setDispatch(DispatchDetail dispatch) {
//        this.dispatch = dispatch;
//    }
    // Getters & Setters


    public List<CDPOSupplierDispatchDTO> getCdpoSupplierDispatches() {
        return cdpoSupplierDispatches;
    }

    public void setCdpoSupplierDispatches(List<CDPOSupplierDispatchDTO> cdpoSupplierDispatches) {
        this.cdpoSupplierDispatches = cdpoSupplierDispatches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

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
