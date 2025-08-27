package com.example.master.Dto;

public class AWCDispatchDTO {

    private Long packagingId;   // mapped from PackagingDetail
    private String awc;
    private String remark;
    private Integer dispatchPackets;

    // Getters & Setters
    public Long getPackagingId() { return packagingId; }
    public void setPackagingId(Long packagingId) { this.packagingId = packagingId; }

    public String getAwc() { return awc; }
    public void setAwc(String awc) { this.awc = awc; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public Integer getDispatchPackets() { return dispatchPackets; }
    public void setDispatchPackets(Integer dispatchPackets) { this.dispatchPackets = dispatchPackets; }
}
