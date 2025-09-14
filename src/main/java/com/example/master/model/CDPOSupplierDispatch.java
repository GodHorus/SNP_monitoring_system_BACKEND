package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cdpo_supplier_dispatch")
public class CDPOSupplierDispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dispatchPackets;   // NEW field
    private String remarks;            // NEW field

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accept_demand_id", nullable = false)
    private AcceptDemand acceptDemand;

    @Column(unique = true, nullable = false)
    private String sublotNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    private Sector sector;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDispatchPackets() {
        return dispatchPackets;
    }

    public void setDispatchPackets(Integer dispatchPackets) {
        this.dispatchPackets = dispatchPackets;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AcceptDemand getAcceptDemand() {
        return acceptDemand;
    }

    public void setAcceptDemand(AcceptDemand acceptDemand) {
        this.acceptDemand = acceptDemand;
    }

    public String getSublotNo() {
        return sublotNo;
    }

    public void setSublotNo(String sublotNo) {
        this.sublotNo = sublotNo;
    }
}