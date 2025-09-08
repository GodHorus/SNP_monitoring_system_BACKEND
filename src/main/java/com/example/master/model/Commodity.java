package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // rice, FR, millets, wheat

    // reverse mapping to quantity
    @OneToMany(mappedBy = "commodity", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("commodity")
    private List<ProductCommodityQuantity> productQuantities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCommodityQuantity> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(List<ProductCommodityQuantity> productQuantities) {
        this.productQuantities = productQuantities;
    }
}
