package com.example.master.Dto;

public class DispatchDetailDTO {
    private Long id;
    private String lotNo;
    private String cdpo;
    private Integer noOfPackets;
    private String remarks;
    private String qrCode;
    private Long packagingId;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLotNo() { return lotNo; }
    public void setLotNo(String lotNo) { this.lotNo = lotNo; }

    public String getCdpo() { return cdpo; }
    public void setCdpo(String cdpo) { this.cdpo = cdpo; }

    public Integer getNoOfPackets() { return noOfPackets; }
    public void setNoOfPackets(Integer noOfPackets) { this.noOfPackets = noOfPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Long getPackagingId() { return packagingId; }
    public void setPackagingId(Long packagingId) { this.packagingId = packagingId; }
}
