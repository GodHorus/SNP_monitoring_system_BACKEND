package com.example.master.Dto;

public class DispatchDetailDTO {

    public Long demandId;
    public String batchNumber; // link by number, not FK
    public String cdpoName;
    public Integer numberOfPackets;
    public String remarks;

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getCdpoName() {
        return cdpoName;
    }

    public void setCdpoName(String cdpoName) {
        this.cdpoName = cdpoName;
    }

    public Integer getNumberOfPackets() {
        return numberOfPackets;
    }

    public void setNumberOfPackets(Integer numberOfPackets) {
        this.numberOfPackets = numberOfPackets;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    //    private Long id;
//    private String lotNo;
//    private Integer noOfPackets;
//    private String remarks;
//    private String qrCode;
//    private Long packagingId;
//    private Long cdpoId;
//
//    private PackagingDetailDTO packagingDetail;
//    private CdpoDTO cdpo;
//
//    public Long getCdpoId() {
//        return cdpoId;
//    }
//
//    public void setCdpoId(Long cdpoId) {
//        this.cdpoId = cdpoId;
//    }
//
//    public PackagingDetailDTO getPackagingDetail() {
//        return packagingDetail;
//    }
//
//    public void setPackagingDetail(PackagingDetailDTO packagingDetail) {
//        this.packagingDetail = packagingDetail;
//    }
//
//    public CdpoDTO getCdpo() {
//        return cdpo;
//    }
//
//    public void setCdpo(CdpoDTO cdpo) {
//        this.cdpo = cdpo;
//    }
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getLotNo() { return lotNo; }
//    public void setLotNo(String lotNo) { this.lotNo = lotNo; }
//
//    public Integer getNoOfPackets() { return noOfPackets; }
//    public void setNoOfPackets(Integer noOfPackets) { this.noOfPackets = noOfPackets; }
//
//    public String getRemarks() { return remarks; }
//    public void setRemarks(String remarks) { this.remarks = remarks; }
//
//    public String getQrCode() { return qrCode; }
//    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
//
//    public Long getPackagingId() { return packagingId; }
//    public void setPackagingId(Long packagingId) { this.packagingId = packagingId; }
}
