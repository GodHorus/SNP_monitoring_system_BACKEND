package com.example.master.Dto;

public class AWCDistributeDTO {

    private Long id;
    private String sublot;
    private String benificiary;
    private Integer hcm;
    private Integer thr;
    private Long dispatchId; // reference to DispatchDetail

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSublot() {
        return sublot;
    }

    public void setSublot(String sublot) {
        this.sublot = sublot;
    }

    public String getBenificiary() {
        return benificiary;
    }

    public void setBenificiary(String benificiary) {
        this.benificiary = benificiary;
    }

    public Integer getHcm() {
        return hcm;
    }

    public void setHcm(Integer hcm) {
        this.hcm = hcm;
    }

    public Integer getThr() {
        return thr;
    }

    public void setThr(Integer thr) {
        this.thr = thr;
    }

    public Long getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Long dispatchId) {
        this.dispatchId = dispatchId;
    }
}
