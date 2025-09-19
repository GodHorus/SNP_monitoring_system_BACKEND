// Ingredient.java
package com.example.master.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // reference to Demand (just the FK)
    @Column(name = "demand_id", nullable = false)
    private Long demandId;

    private String name;
    private BigDecimal quantity;
    private String unit;

    // constructors, getters, setters
    public Ingredient() {}
    public Ingredient(Long demandId, String name, BigDecimal quantity, String unit) {
        this.demandId = demandId; this.name = name; this.quantity = quantity; this.unit = unit;
    }
    // getters/setters...
    public Long getId() { return id; }
    public Long getDemandId() { return demandId; }
    public void setDemandId(Long demandId) { this.demandId = demandId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
