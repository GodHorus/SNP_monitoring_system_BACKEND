package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "accept_demands")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AcceptDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer receivedPackets;   // NEW field
    private String remarks;            // NEW field

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatch_id", nullable = false)
    @JsonBackReference
    private DispatchDetail dispatchDetail;

    private String qrCode;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getReceivedPackets() { return receivedPackets; }
    public void setReceivedPackets(Integer receivedPackets) { this.receivedPackets = receivedPackets; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public DispatchDetail getDispatchDetail() { return dispatchDetail; }
    public void setDispatchDetail(DispatchDetail dispatchDetail) { this.dispatchDetail = dispatchDetail; }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
