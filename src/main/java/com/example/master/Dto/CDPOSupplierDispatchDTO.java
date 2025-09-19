package com.example.master.Dto;

public class CDPOSupplierDispatchDTO {

    private Long id;
    private Long demandId;
    private String batchNo;
    private String lotNo;
    private String cdpo;
    private String sector;
    private Integer dispatchPackets;
    private String remarks;
    private String sublotNo;

    // getters & setters
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

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public Integer getDispatchPackets() { return dispatchPackets; }
    public void setDispatchPackets(Integer dispatchPackets) { this.dispatchPackets = dispatchPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getSublotNo() { return sublotNo; }
    public void setSublotNo(String sublotNo) { this.sublotNo = sublotNo; }
}
