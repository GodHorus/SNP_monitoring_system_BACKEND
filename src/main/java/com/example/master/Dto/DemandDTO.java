package com.example.master.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class DemandDTO {

    private Long id; // for update/response

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    @NotBlank(message = "Demand category is required")
    private String demandCategory;

    @NotBlank(message = "Demand product is required")
    private String demandProduct;

    private String beneficery; // optional

    @NotBlank(message = "FCI ID is required")
    private String fciId;

    private String fciDocs; // optional

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotBlank(message = "Quantity unit is required")
    private String quantityUnit;

//    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    private String supplierDocs; // optional

    @NotNull(message = "District ID is required")
    private Long districtId;

    @NotNull(message = "CDPO ID is required")
    private Long cdpoId;

    @NotNull(message = "Sector ID is required")
    private Long sectorId;

    private String rejectionReason;
    private String notes;

    private List<@Valid SupplierMappingDTO> supplierIds;

    @NotEmpty(message = "AWC details are required")
    private List<@Valid DemandAwcDetailDTO> awcDetails;

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

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getCdpoId() {
        return cdpoId;
    }

    public void setCdpoId(Long cdpoId) {
        this.cdpoId = cdpoId;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
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

    public List<DemandAwcDetailDTO> getAwcDetails() {
        return awcDetails;
    }

    public void setAwcDetails(List<DemandAwcDetailDTO> awcDetails) {
        this.awcDetails = awcDetails;
    }

    public List<SupplierMappingDTO> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<SupplierMappingDTO> supplierIds) {
        this.supplierIds = supplierIds;
    }
}
