package com.example.master.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredient_details")
public class IngredientDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;
    private String unit;
    private String vendor;
    private BigDecimal total;
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String batchNo;

    // Relation with Demand
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_id", nullable = false)
    private Demand demand;

    // Relation with LabReport
    @OneToOne(mappedBy = "ingredientDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LabReport labReport;

    // Getters and Setters
    // ...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public LabReport getLabReport() {
        return labReport;
    }

    public void setLabReport(LabReport labReport) {
        this.labReport = labReport;
    }
}
