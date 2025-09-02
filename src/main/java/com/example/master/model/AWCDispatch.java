package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "awc_dispatch")
public class AWCDispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String awc;
    private String remark;
    private Integer dispatchPackets;

    // Mapping with PackagingDetail (batchNo, lotNo, cdpo, qrCode can be derived)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", nullable = false)
    private PackagingDetail packagingDetail;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAwc() { return awc; }
    public void setAwc(String awc) { this.awc = awc; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public Integer getDispatchPackets() { return dispatchPackets; }
    public void setDispatchPackets(Integer dispatchPackets) { this.dispatchPackets = dispatchPackets; }

    public PackagingDetail getPackagingDetail() { return packagingDetail; }
    public void setPackagingDetail(PackagingDetail packagingDetail) { this.packagingDetail = packagingDetail; }
}
