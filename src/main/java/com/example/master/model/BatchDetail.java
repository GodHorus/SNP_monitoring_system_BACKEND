package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "batch_details")
public class BatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String batchNo;   // b1, b2, b3
    private Double quantity;
    private String qrCode;

    // Relation with LabReport
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lab_report_id")
    private LabReport labReport;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public LabReport getLabReport() { return labReport; }
    public void setLabReport(LabReport labReport) { this.labReport = labReport; }
}
