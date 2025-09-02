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

    private LocalDateTime fciAcceptedAt;
    private LocalDateTime fciRejectedAt;
    private LocalDateTime supplierAcceptedAt;
    private LocalDateTime supplierRejectedAt;
    private LocalDateTime cdpoDispatchedAt;
    private LocalDateTime awcAcceptedAt;

    private String demandCategory;

    private String demandProduct;

    private String beneficery;

    private String rejectionReason;
    private String notes;

    private DistrictDTO district;
    private CdpoDTO cdpo;
    private SectorDTO sectorDTO;

    private List<DemandAwcDetailDTO> awcDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters + setters


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

    public String getDemandCategory() {
        return demandCategory;
    }

    public void setDemandCategory(String demandCategory) {
        this.demandCategory = demandCategory;
    }

    public String getDemandProduct() {
        return demandProduct;
    }

    public void setDemandProduct(String demandProduct) {
        this.demandProduct = demandProduct;
    }

    public String getBeneficery() {
        return beneficery;
    }

    public void setBeneficery(String beneficery) {
        this.beneficery = beneficery;
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

    public LocalDateTime getFciAcceptedAt() {
        return fciAcceptedAt;
    }

    public void setFciAcceptedAt(LocalDateTime fciAcceptedAt) {
        this.fciAcceptedAt = fciAcceptedAt;
    }

    public LocalDateTime getFciRejectedAt() {
        return fciRejectedAt;
    }

    public void setFciRejectedAt(LocalDateTime fciRejectedAt) {
        this.fciRejectedAt = fciRejectedAt;
    }

    public LocalDateTime getSupplierAcceptedAt() {
        return supplierAcceptedAt;
    }

    public void setSupplierAcceptedAt(LocalDateTime supplierAcceptedAt) {
        this.supplierAcceptedAt = supplierAcceptedAt;
    }

    public LocalDateTime getSupplierRejectedAt() {
        return supplierRejectedAt;
    }

    public void setSupplierRejectedAt(LocalDateTime supplierRejectedAt) {
        this.supplierRejectedAt = supplierRejectedAt;
    }

    public LocalDateTime getCdpoDispatchedAt() {
        return cdpoDispatchedAt;
    }

    public void setCdpoDispatchedAt(LocalDateTime cdpoDispatchedAt) {
        this.cdpoDispatchedAt = cdpoDispatchedAt;
    }

    public LocalDateTime getAwcAcceptedAt() {
        return awcAcceptedAt;
    }

    public void setAwcAcceptedAt(LocalDateTime awcAcceptedAt) {
        this.awcAcceptedAt = awcAcceptedAt;
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

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public CdpoDTO getCdpo() {
        return cdpo;
    }

    public void setCdpo(CdpoDTO cdpo) {
        this.cdpo = cdpo;
    }

    public SectorDTO getSectorDTO() {
        return sectorDTO;
    }

    public void setSectorDTO(SectorDTO sectorDTO) {
        this.sectorDTO = sectorDTO;
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
