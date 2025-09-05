package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_commodity_quantity")
public class ProductCommodityQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity; // input by user

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commodity_id", nullable = false)
    private Commodity commodity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_product_id", nullable = false)
    private DemandProduct demandProduct;

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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public DemandProduct getDemandProduct() {
        return demandProduct;
    }

    public void setDemandProduct(DemandProduct demandProduct) {
        this.demandProduct = demandProduct;
    }
}
