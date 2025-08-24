package com.example.master.Dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class DemandDTO {

    @NotNull(message = "From date is required")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    @NotBlank(message = "FCI ID is required")
    private String fciId;

    private String fciDocs; // Optional, no validation

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @NotBlank(message = "Quantity unit is required")
    private String quantityUnit;

    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    private String supplierDocs; // Optional, no validation

    @NotEmpty(message = "At least one district must be selected")
    private List<@NotBlank(message = "District cannot be blank") String> selectedDistricts;

    @NotEmpty(message = "At least one CDPO must be selected")
    private List<@NotBlank(message = "CDPO ID cannot be blank") String> cdpo;

    @NotEmpty(message = "At least one supervisor must be selected")
    private List<@NotBlank(message = "Supervisor ID cannot be blank") String> supervisorId;

    @NotBlank(message = "AWC details are required")
    private String awcDetailsJson;

    private String description; // Optional

    // Getters and Setters

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public List<String> getSelectedDistricts() {
        return selectedDistricts;
    }

    public void setSelectedDistricts(List<String> selectedDistricts) {
        this.selectedDistricts = selectedDistricts;
    }

    public List<String> getCdpo() {
        return cdpo;
    }

    public void setCdpo(List<String> cdpo) {
        this.cdpo = cdpo;
    }

    public List<String> getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(List<String> supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getAwcDetailsJson() {
        return awcDetailsJson;
    }

    public void setAwcDetailsJson(String awcDetailsJson) {
        this.awcDetailsJson = awcDetailsJson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
