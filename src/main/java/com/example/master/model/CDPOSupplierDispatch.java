package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "cdpo_supplier_dispatch")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CDPOSupplierDispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demand_id", nullable = false)
    private Long demandId;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "cdpo")
    private String cdpo;   // CDPO/Project Name

    @Column(name = "sector")
    private String sector;

    @Column(name = "dispatch_packets")
    private Integer dispatchPackets;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "sublot_no", unique = true, nullable = false)
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
