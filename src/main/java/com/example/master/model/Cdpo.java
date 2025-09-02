package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cdpos")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cdpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cdpo_name", nullable = false)
    private String cdpoName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonBackReference
//    @JsonIgnore
    private District district;

//    @OneToMany(mappedBy = "cdpo")
//    @JsonManagedReference
//    private List<Supervisor> supervisors;

    @OneToMany(mappedBy = "cdpo")  // One Cdpo can have multiple sectors
    private List<Sector> sectors;

    public Cdpo() {}
    public Cdpo(String cdpoName) { this.cdpoName = cdpoName; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public String getCdpoName() {
        return cdpoName;
    }

    public void setCdpoName(String cdpoName) {
        this.cdpoName = cdpoName;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

//    public List<Supervisor> getSupervisors() {
//        return supervisors;
//    }
//
//    public void setSupervisors(List<Supervisor> supervisors) {
//        this.supervisors = supervisors;
//    }
}
