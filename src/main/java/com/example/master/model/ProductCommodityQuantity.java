package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "product_commodity_quantity")
public class ProductCommodityQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity; // input by user

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "commodity_id", nullable = false)
//    @JsonBackReference
//    private Commodity commodity;

    @Column(name = "commodity")
    private String commodity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "demand_product", nullable = false)
    @JsonBackReference
    @Column(name = "demand_product")
    private String demandProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_id", nullable = false)
    @JsonBackReference
    private Demand demand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
//
//    public Commodity getCommodity() {
//        return commodity;
//    }
//
//    public void setCommodity(Commodity commodity) {
//        this.commodity = commodity;
//    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

//    public DemandProduct getDemandProduct() {
//        return demandProduct;
//    }
//
//    public void setDemandProduct(DemandProduct demandProduct) {
//        this.demandProduct = demandProduct;
//    }


    public String getDemandProduct() {
        return demandProduct;
    }

    public void setDemandProduct(String demandProduct) {
        this.demandProduct = demandProduct;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }
}
