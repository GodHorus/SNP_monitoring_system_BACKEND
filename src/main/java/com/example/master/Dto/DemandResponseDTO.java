package com.example.master.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DemandResponseDTO {
    private Long id;
    private String description;
    private String status;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String fciId;
    private String fciDocs;

    private Integer quantity;
    private String quantityUnit;

    private String supplierId;
    private String supplierDocs;

    private String rejectionReason;
    private String notes;

    private Long districtId;
    private String districtName;

    private Long cdpoId;
    private String cdpoName;

    private Long supervisorId;
    private String supervisorName;

    private List<DemandAwcDetailDTO> awcDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getFciId() {
        return fciId;
    }

    public void setFciId(String fciId) {
        this.fciId = fciId;
    }

    public String getFciDocs() {
        return fciDocs;
    }

    public void setFciDocs(String fciDocs) {
        this.fciDocs = fciDocs;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierDocs() {
        return supplierDocs;
    }

    public void setSupplierDocs(String supplierDocs) {
        this.supplierDocs = supplierDocs;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getCdpoId() {
        return cdpoId;
    }

    public void setCdpoId(Long cdpoId) {
        this.cdpoId = cdpoId;
    }

    public String getCdpoName() {
        return cdpoName;
    }

    public void setCdpoName(String cdpoName) {
        this.cdpoName = cdpoName;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public List<DemandAwcDetailDTO> getAwcDetails() {
        return awcDetails;
    }

    public void setAwcDetails(List<DemandAwcDetailDTO> awcDetails) {
        this.awcDetails = awcDetails;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
