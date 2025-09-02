package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "demand_awc_details")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DemandAwcDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation to Demand
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private Demand demand;

    // Relation to Anganwadi Center
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anganwadi_id", nullable = false)
    private AnganwadiCenter anganwadi;


    private Integer quantity;

    private String type;

    // Meals
//    private Integer hcmNumber;
//    private String hcmUnit; // "kg", "packets", etc.
//
//    private Integer thrNumber;
//    private String thrUnit; // "kg", "packets", etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public AnganwadiCenter getAnganwadi() {
        return anganwadi;
    }

    public void setAnganwadi(AnganwadiCenter anganwadi) {
        this.anganwadi = anganwadi;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //    public Integer getHcmNumber() {
//        return hcmNumber;
//    }
//
//    public void setHcmNumber(Integer hcmNumber) {
//        this.hcmNumber = hcmNumber;
//    }
//
//    public String getHcmUnit() {
//        return hcmUnit;
//    }
//
//    public void setHcmUnit(String hcmUnit) {
//        this.hcmUnit = hcmUnit;
//    }
//
//    public Integer getThrNumber() {
//        return thrNumber;
//    }
//
//    public void setThrNumber(Integer thrNumber) {
//        this.thrNumber = thrNumber;
//    }
//
//    public String getThrUnit() {
//        return thrUnit;
//    }
//
//    public void setThrUnit(String thrUnit) {
//        this.thrUnit = thrUnit;
//    }
}
