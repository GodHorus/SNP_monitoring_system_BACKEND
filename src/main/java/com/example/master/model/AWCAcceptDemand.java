package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "awc_accept_demand")
public class AWCAcceptDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer recievednumber;
    private String remarks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatch_id", nullable = false)
    private DispatchDetail dispatchDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRecievednumber() {
        return recievednumber;
    }

    public void setRecievednumber(Integer recievednumber) {
        this.recievednumber = recievednumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public DispatchDetail getDispatchDetail() {
        return dispatchDetail;
    }

    public void setDispatchDetail(DispatchDetail dispatchDetail) {
        this.dispatchDetail = dispatchDetail;
    }
}
