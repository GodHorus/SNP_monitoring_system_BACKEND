package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supplier_mapping")
public class SupplierMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many mappings can belong to one demand
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_id")
    @JsonBackReference
    private Demand demand;

    // Supplier linked
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // One district per mapping
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;

    // Multiple CDPOs per supplier mapping
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "supplier_mapping_cdpos",
            joinColumns = @JoinColumn(name = "supplier_mapping_id"),
            inverseJoinColumns = @JoinColumn(name = "cdpo_id")
    )
    private List<Cdpo> cdpos;

    // Multiple Sectors per supplier mapping
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "supplier_mapping_sectors",
            joinColumns = @JoinColumn(name = "supplier_mapping_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id")
    )
    private List<Sector> sectors;

    // Getters & Setters


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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Cdpo> getCdpos() {
        return cdpos;
    }

    public void setCdpos(List<Cdpo> cdpos) {
        this.cdpos = cdpos;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }
}
