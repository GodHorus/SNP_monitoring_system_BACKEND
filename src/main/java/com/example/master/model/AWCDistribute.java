package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "awc_distribut")
public class AWCDistribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sublot;
    private String benificiary;
    private Integer hcm;

    private Integer thr;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatch_id", nullable = false)
    private DispatchDetail dispatchDetail;


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

    public DispatchDetail getDispatchDetail() {
        return dispatchDetail;
    }

    public void setDispatchDetail(DispatchDetail dispatchDetail) {
        this.dispatchDetail = dispatchDetail;
    }
}
