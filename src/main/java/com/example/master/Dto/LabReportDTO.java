package com.example.master.Dto;

import java.time.LocalDate;

public class LabReportDTO {

    public Long demandId;
    private Long id;
    private String labName;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private LocalDate testDate;
    private String status;
    private String remarks;
    private String filePath;

    // ✅ link via batchId (NOT ingredientId anymore)
//    private Long batchId;


//    public LabReportDTO(Long id, String labName, LocalDate manufacturingDate, LocalDate expiryDate, LocalDate testDate, String status, String remarks, String filePath) {
//        this.id = id;
//        this.labName = labName;
//        this.manufacturingDate = manufacturingDate;
//        this.expiryDate = expiryDate;
//        this.testDate = testDate;
//        this.status = status;
//        this.remarks = remarks;
//        this.filePath = filePath;
//    }

    public LabReportDTO() {

    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }



//    public Long getBatchId() { return batchId; }
//    public void setBatchId(Long batchId) { this.batchId = batchId; }
}
