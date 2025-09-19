
// DispatchDetail.java
package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dispatch_details")
public class DispatchDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demand_id", nullable = false)
    private Long demandId;

    @Column(name = "lot_number", unique = true)
    private String lotNumber; // L-1

    private String cdpoName;
    private Integer numberOfPackets;
    private String remarks;

    @Column(name = "batch_number")
    private String batchNumber;

    public DispatchDetail(){}
    // getters/setters...
    public Long getId(){ return id; }
    public Long getDemandId(){ return demandId; }
    public void setDemandId(Long demandId){ this.demandId = demandId; }
    public String getLotNumber(){ return lotNumber; }
    public void setLotNumber(String lotNumber){ this.lotNumber = lotNumber; }
    public String getCdpoName(){ return cdpoName; }
    public void setCdpoName(String cdpoName){ this.cdpoName = cdpoName; }
    public Integer getNumberOfPackets(){ return numberOfPackets; }
    public void setNumberOfPackets(Integer numberOfPackets){ this.numberOfPackets = numberOfPackets; }
    public String getRemarks(){ return remarks; }
    public void setRemarks(String remarks){ this.remarks = remarks; }
    public String getBatchNumber(){ return batchNumber; }
    public void setBatchNumber(String batchNumber){ this.batchNumber = batchNumber; }
}



//package com.example.master.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "dispatch_details")
//public class DispatchDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String lotNo;
////    private String cdpo;
//    private Integer noOfPackets;
//    private String remarks;
//    @Column(name = "qr_code", length = 1024)
//    private String qrCode;
//
//    // Relation with PackagingDetail
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "packaging_id", nullable = false)
////    private PackagingDetail packagingDetail;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "packaging_id", nullable = false)
//    private PackagingDetail packagingDetail;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cdpo_id", nullable = false)
//    private Cdpo cdpo;
//
//    public Cdpo getCdpo() {
//        return cdpo;
//    }
//
//    public void setCdpo(Cdpo cdpo) {
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
////    public String getCdpo() { return cdpo; }
////    public void setCdpo(String cdpo) { this.cdpo = cdpo; }
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
//    public PackagingDetail getPackagingDetail() { return packagingDetail; }
//    public void setPackagingDetail(PackagingDetail packagingDetail) { this.packagingDetail = packagingDetail; }
//}
