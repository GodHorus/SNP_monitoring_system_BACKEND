package com.example.master.Dto;

import jakarta.validation.Valid;
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

    private String fciDocs; // Optional

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @NotBlank(message = "Quantity unit is required")
    private String quantityUnit;

    @NotBlank(message = "Supplier ID is required")
    private String supplierId;

    private String supplierDocs; // Optional

    @NotNull(message = "District ID is required")
    private Long districtId;

    @NotNull(message = "CDPO ID is required")
    private Long cdpoId;

    @NotNull(message = "Supervisor ID is required")
    private Long supervisorId;

    @NotEmpty(message = "AWC details are required")
    private List<@Valid DemandAwcDetailDTO> awcDetails;

    private String description; // Optional

    // Getters and Setters
    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public String getFciId() { return fciId; }
    public void setFciId(String fciId) { this.fciId = fciId; }

    public String getFciDocs() { return fciDocs; }
    public void setFciDocs(String fciDocs) { this.fciDocs = fciDocs; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getQuantityUnit() { return quantityUnit; }
    public void setQuantityUnit(String quantityUnit) { this.quantityUnit = quantityUnit; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    public String getSupplierDocs() { return supplierDocs; }
    public void setSupplierDocs(String supplierDocs) { this.supplierDocs = supplierDocs; }

    public Long getDistrictId() { return districtId; }
    public void setDistrictId(Long districtId) { this.districtId = districtId; }

    public Long getCdpoId() { return cdpoId; }
    public void setCdpoId(Long cdpoId) { this.cdpoId = cdpoId; }

    public Long getSupervisorId() { return supervisorId; }
    public void setSupervisorId(Long supervisorId) { this.supervisorId = supervisorId; }

    public List<DemandAwcDetailDTO> getAwcDetails() { return awcDetails; }
    public void setAwcDetails(List<DemandAwcDetailDTO> awcDetails) { this.awcDetails = awcDetails; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
