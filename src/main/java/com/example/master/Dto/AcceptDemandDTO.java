package com.example.master.Dto;

public class AcceptDemandDTO {

    private Long id;
    private Long demandId;
    private String batchNo;
    private String lotNo;
    private String cdpo;
    private Integer noOfPackets;
    private Integer receivedPackets;
    private String remarks;
    private String qrCode;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDemandId() { return demandId; }
    public void setDemandId(Long demandId) { this.demandId = demandId; }

    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }

    public String getLotNo() { return lotNo; }
    public void setLotNo(String lotNo) { this.lotNo = lotNo; }

    public String getCdpo() { return cdpo; }
    public void setCdpo(String cdpo) { this.cdpo = cdpo; }

    public Integer getNoOfPackets() { return noOfPackets; }
    public void setNoOfPackets(Integer noOfPackets) { this.noOfPackets = noOfPackets; }

    public Integer getReceivedPackets() { return receivedPackets; }
    public void setReceivedPackets(Integer receivedPackets) { this.receivedPackets = receivedPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}
