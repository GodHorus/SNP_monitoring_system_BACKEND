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
    private Integer totalDays;
    private String notes;
    private String fciDocs;
    private String supplierDocs;

    private DemandCategoryDTO demandCategory;
    private BeneficiaryDTO beneficiary;
    private SupplierDTO supplier;
    private DistrictDTO district;
    private FciDTO fci;

    private List<DemandCdpoDetailResponseDTO> cdpoDetails;
    private ProductQuantityResponse productQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFciDocs() {
        return fciDocs;
    }

    public void setFciDocs(String fciDocs) {
        this.fciDocs = fciDocs;
    }

    public String getSupplierDocs() {
        return supplierDocs;
    }

    public void setSupplierDocs(String supplierDocs) {
        this.supplierDocs = supplierDocs;
    }

    public DemandCategoryDTO getDemandCategory() {
        return demandCategory;
    }

    public void setDemandCategory(DemandCategoryDTO demandCategory) {
        this.demandCategory = demandCategory;
    }

    public BeneficiaryDTO getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryDTO beneficiary) {
        this.beneficiary = beneficiary;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public FciDTO getFci() {
        return fci;
    }

    public void setFci(FciDTO fci) {
        this.fci = fci;
    }

    public List<DemandCdpoDetailResponseDTO> getCdpoDetails() {
        return cdpoDetails;
    }

    public void setCdpoDetails(List<DemandCdpoDetailResponseDTO> cdpoDetails) {
        this.cdpoDetails = cdpoDetails;
    }

    public ProductQuantityResponse getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(ProductQuantityResponse productQuantity) {
        this.productQuantity = productQuantity;
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
