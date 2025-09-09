package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dispatch_details")
public class DispatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lotNo;
//    private String cdpo;
    private Integer noOfPackets;
    private String remarks;
    private String qrCode;

    // Relation with PackagingDetail
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", nullable = false)
    private PackagingDetail packagingDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdpo_id", nullable = false)
    private Cdpo cdpo;

    public Cdpo getCdpo() {
        return cdpo;
    }

    public void setCdpo(Cdpo cdpo) {
        this.cdpo = cdpo;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLotNo() { return lotNo; }
    public void setLotNo(String lotNo) { this.lotNo = lotNo; }

//    public String getCdpo() { return cdpo; }
//    public void setCdpo(String cdpo) { this.cdpo = cdpo; }

    public Integer getNoOfPackets() { return noOfPackets; }
    public void setNoOfPackets(Integer noOfPackets) { this.noOfPackets = noOfPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public PackagingDetail getPackagingDetail() { return packagingDetail; }
    public void setPackagingDetail(PackagingDetail packagingDetail) { this.packagingDetail = packagingDetail; }
}
