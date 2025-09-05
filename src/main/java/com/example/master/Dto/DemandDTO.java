package com.example.master.Dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class DemandDTO {

    private Long id;
    private String description;
    private String status;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer totalDays;
    private Long demandCategoryId;
    private Long beneficiaryId;
    private Long fciId;
    private String fciDocs;
    private Long supplierId;
    private String supplierDocs;
    private Long districtId;

//    private List<DemandProductDTO> demandProducts;
    private List<DemandCdpoDetailRequestDTO> cdpoDetails;
    private ProductQuantityRequest productQuantities;

    private String notes;

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

    public Long getDemandCategoryId() {
        return demandCategoryId;
    }

    public void setDemandCategoryId(Long demandCategoryId) {
        this.demandCategoryId = demandCategoryId;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public Long getFciId() {
        return fciId;
    }

    public void setFciId(Long fciId) {
        this.fciId = fciId;
    }

    public String getFciDocs() {
        return fciDocs;
    }

    public void setFciDocs(String fciDocs) {
        this.fciDocs = fciDocs;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<DemandCdpoDetailRequestDTO> getCdpoDetails() {
        return cdpoDetails;
    }

    public void setCdpoDetails(List<DemandCdpoDetailRequestDTO> cdpoDetails) {
        this.cdpoDetails = cdpoDetails;
    }

    public ProductQuantityRequest getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(ProductQuantityRequest productQuantities) {
        this.productQuantities = productQuantities;
    }
}
