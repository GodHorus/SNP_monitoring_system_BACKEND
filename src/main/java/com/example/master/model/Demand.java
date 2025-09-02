package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "demands")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String status;

    private LocalDate fromDate;
    private LocalDate toDate;

    private String demandCategory;

    private String demandProduct;

    private String beneficery;

    private String fciId;
    private String fciDocs;

    private Integer quantity;
    private String quantityUnit; // "kg", "packets", "metric ton"

    private String supplierId;
    private String supplierDocs;

    // Workflow timestamps
    private LocalDateTime fciAcceptedAt;
    private LocalDateTime fciRejectedAt;
    private LocalDateTime supplierAcceptedAt;
    private LocalDateTime supplierRejectedAt;
    private LocalDateTime cdpoDispatchedAt;
    private LocalDateTime awcAcceptedAt;

    private String rejectionReason;
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
//    @JsonIgnore
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cdpo_id")
//    @JsonIgnore
    private Cdpo cdpo;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "supervisor_id")
    /// /    @JsonIgnore
//    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id")
    private Sector sectors;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DemandAwcDetail> awcDetails;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) this.status = "NEW";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public Demand() {
    }

//     getteer settere

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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Cdpo getCdpo() {
        return cdpo;
    }

    public void setCdpo(Cdpo cdpo) {
        this.cdpo = cdpo;
    }

//    public Supervisor getSupervisor() {
//        return supervisor;
//    }
//
//    public void setSupervisor(Supervisor supervisor) {
//        this.supervisor = supervisor;
//    }


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

    public Sector getSectors() {
        return sectors;
    }

    public void setSectors(Sector sectors) {
        this.sectors = sectors;
    }

    public List<DemandAwcDetail> getAwcDetails() {
        return awcDetails;
    }

    public void setAwcDetails(List<DemandAwcDetail> awcDetails) {
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
