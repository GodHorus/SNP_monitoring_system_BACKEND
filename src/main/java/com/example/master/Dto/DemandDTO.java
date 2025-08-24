package com.example.master.Dto;

import java.time.LocalDate;
import java.util.List;

public class DemandDTO {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String fciId;
    private String fciDocs;
    private int quantity;
    private String quantityUnit;
    private String supplierId;
    private String supplierDocs;
    private List<String> selectedDistricts;
    private List<String> cdpo;
    private List<String> supervisorId;
    private String awcDetailsJson;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // getters & setters
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

    public List<String> getSelectedDistricts() { return selectedDistricts; }
    public void setSelectedDistricts(List<String> selectedDistricts) { this.selectedDistricts = selectedDistricts; }

    public List<String> getCdpo() { return cdpo; }
    public void setCdpo(List<String> cdpo) { this.cdpo = cdpo; }

    public List<String> getSupervisorId() { return supervisorId; }
    public void setSupervisorId(List<String> supervisorId) { this.supervisorId = supervisorId; }

    public String getAwcDetailsJson() { return awcDetailsJson; }
    public void setAwcDetailsJson(String awcDetailsJson) { this.awcDetailsJson = awcDetailsJson; }
}
