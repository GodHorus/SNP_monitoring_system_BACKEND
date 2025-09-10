package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "demands")
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String status;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer totalDays;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "demand_category_id")
    private DemandCategory demandCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beneficiary_id")
    private Benificiary beneficery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fci_id")
    private Fci fci;
    private String fciDocs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private String supplierDocs;

    // Workflow timestamps
    private LocalDateTime fciAcceptedAt;
    private LocalDateTime fciRejectedAt;
    private LocalDateTime fciDispatchedAt;
    private LocalDateTime supplierAcceptedAt;
    private LocalDateTime supplierRejectedAt;
    private LocalDateTime supplierSelfDeclaredAt;
    private LocalDateTime supplierDispatchedAt;
    private LocalDateTime cdpoDispatchedAt;
    private LocalDateTime awcAcceptedAt;
    private LocalDateTime awcDistributedAt;
    private String rejectionReason;
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;


    @Column(name = "demand_product")
    private String demandProducts;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductCommodityQuantity> productQuantities;

    @OneToMany(mappedBy = "demand", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DemandCdpoDetail> cdpoDetails;

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

    public String getFciDocs() {
        return fciDocs;
    }

    public void setFciDocs(String fciDocs) {
        this.fciDocs = fciDocs;
    }

    public List<DemandCdpoDetail> getCdpoDetails() {
        return cdpoDetails;
    }

    public void setCdpoDetails(List<DemandCdpoDetail> cdpoDetails) {
        this.cdpoDetails = cdpoDetails;
    }

    public List<ProductCommodityQuantity> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(List<ProductCommodityQuantity> productQuantities) {
        this.productQuantities = productQuantities;
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

//    public List<DemandProduct> getDemandProducts() {
//        return demandProducts;
//    }
//
//    public void setDemandProducts(List<DemandProduct> demandProducts) {
//        this.demandProducts = demandProducts;
//    }

    public DemandCategory getDemandCategory() {
        return demandCategory;
    }

    public void setDemandCategory(DemandCategory demandCategory) {
        this.demandCategory = demandCategory;
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

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Fci getFci() {
        return fci;
    }

    public void setFci(Fci fci) {
        this.fci = fci;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getFciDispatchedAt() {
        return fciDispatchedAt;
    }

    public void setFciDispatchedAt(LocalDateTime fciDispatchedAt) {
        this.fciDispatchedAt = fciDispatchedAt;
    }

    public LocalDateTime getSupplierSelfDeclaredAt() {
        return supplierSelfDeclaredAt;
    }

    public void setSupplierSelfDeclaredAt(LocalDateTime supplierSelfDeclaredAt) {
        this.supplierSelfDeclaredAt = supplierSelfDeclaredAt;
    }

    public LocalDateTime getSupplierDispatchedAt() {
        return supplierDispatchedAt;
    }

    public void setSupplierDispatchedAt(LocalDateTime supplierDispatchedAt) {
        this.supplierDispatchedAt = supplierDispatchedAt;
    }

    public LocalDateTime getAwcDistributedAt() {
        return awcDistributedAt;
    }

    public void setAwcDistributedAt(LocalDateTime awcDistributedAt) {
        this.awcDistributedAt = awcDistributedAt;
    }

    public Benificiary getBeneficery() {
        return beneficery;
    }

    public void setBeneficery(Benificiary beneficery) {
        this.beneficery = beneficery;
    }

//    public List<DemandProduct> getDemandProducts() {
//        return demandProducts;
//    }
//
//    public void setDemandProducts(List<DemandProduct> demandProducts) {
//        this.demandProducts = demandProducts;
//    }

    public String getDemandProducts() {
        return demandProducts;
    }

    public void setDemandProducts(String demandProducts) {
        this.demandProducts = demandProducts;
    }
}
