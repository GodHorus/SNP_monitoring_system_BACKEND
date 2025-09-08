package com.example.master.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Table(name = "demand_product")
public class DemandProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

//    @OneToMany(mappedBy = "demandProduct", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<ProductCommodityQuantity> productQuantities;

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


//    public List<ProductCommodityQuantity> getProductQuantities() {
//        return productQuantities;
//    }
//
//    public void setProductQuantities(List<ProductCommodityQuantity> productQuantities) {
//        this.productQuantities = productQuantities;
//    }
}