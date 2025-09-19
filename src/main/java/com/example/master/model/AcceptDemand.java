package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "accept_demands")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AcceptDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demand_id", nullable = false)
    private Long demandId;   // only demandId as FK (not mapped)

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "cdpo")
    private String cdpo;   // store cdpo name as String

    @Column(name = "no_of_packets")
    private Integer noOfPackets;

    @Column(name = "received_packets")
    private Integer receivedPackets;

    private String remarks;

    @Column(name = "qr_code", length = 1024)
    private String qrCode;

    // getters and setters
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

    public Integer getNoOfPackets() { return noOfPackets; }
    public void setNoOfPackets(Integer noOfPackets) { this.noOfPackets = noOfPackets; }

    public Integer getReceivedPackets() { return receivedPackets; }
    public void setReceivedPackets(Integer receivedPackets) { this.receivedPackets = receivedPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}
