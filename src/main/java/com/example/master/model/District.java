package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "districts")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="district_name", nullable = false, unique = true)
    private String districtName;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Cdpo> cdpos = new ArrayList<>();

    public District() {}
    public District(String districtName) { this.districtName = districtName; }

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<Cdpo> getCdpos() {
        return cdpos;
    }

    public void setCdpos(List<Cdpo> cdpos) {
        this.cdpos = cdpos;
    }
}
