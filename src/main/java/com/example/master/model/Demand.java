package com.example.master.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "demands")
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    /**
     * Workflow Status:
     * NEW, FCI_ACCEPTED, FCI_REJECTED,
     * SUPPLIER_ACCEPTED, SUPPLIER_REJECTED,
     * CDPO_DISPATCHED, AWC_ACCEPTED, COMPLETED
     */
    private String status;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String fciId;
    private String fciDocs;

    private Integer quantity;
    private String quantityUnit;

    private String supplierId;
    private String supplierDocs;

    // DWCD selections
    @ElementCollection
    @CollectionTable(name = "demand_districts", joinColumns = @JoinColumn(name = "demand_id"))
    @Column(name = "district")
    private List<String> districts;

    @ElementCollection
    @CollectionTable(name = "demand_blocks", joinColumns = @JoinColumn(name = "demand_id"))
    @Column(name = "cdpo")
    private List<String> cdpos;

    @ElementCollection
    @CollectionTable(name = "demand_supervisors", joinColumns = @JoinColumn(name = "demand_id"))
    @Column(name = "supervisor")
    private List<String> supervisors;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String awcDetailsJson; // store AWC details as JSON string

    // Audit Fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // JPA lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "NEW";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public String getFciId() { return fciId; }
    public void setFciId(String fciId) { this.fciId = fciId; }

    public String getFciDocs() { return fciDocs; }
    public void setFciDocs(String fciDocs) { this.fciDocs = fciDocs; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getQuantityUnit() { return quantityUnit; }
    public void setQuantityUnit(String quantityUnit) { this.quantityUnit = quantityUnit; }

    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    public String getSupplierDocs() { return supplierDocs; }
    public void setSupplierDocs(String supplierDocs) { this.supplierDocs = supplierDocs; }

    public List<String> getDistricts() { return districts; }
    public void setDistricts(List<String> districts) { this.districts = districts; }

    public List<String> getCdpos() { return cdpos; }
    public void setCdpos(List<String> cdpos) { this.cdpos = cdpos; }

    public List<String> getSupervisors() { return supervisors; }
    public void setSupervisors(List<String> supervisors) { this.supervisors = supervisors; }

    public String getAwcDetailsJson() { return awcDetailsJson; }
    public void setAwcDetailsJson(String awcDetailsJson) { this.awcDetailsJson = awcDetailsJson; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
