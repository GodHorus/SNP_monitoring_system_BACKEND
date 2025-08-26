package com.example.master.Dto;

public class BatchDetailDTO {
    private Long id;
    private String type;
    private String batchNo;
    private Double quantity;
    private String qrCode;
    private Long labReportId;

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

    public Long getLabReportId() { return labReportId; }
    public void setLabReportId(Long labReportId) { this.labReportId = labReportId; }
}
