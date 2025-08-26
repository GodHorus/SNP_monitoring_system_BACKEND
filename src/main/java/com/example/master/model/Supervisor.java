package com.example.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "supervisors")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdpo_id")
    @JsonBackReference
    private Cdpo cdpo;

    @OneToMany(mappedBy = "supervisor")
    @JsonManagedReference
    private List<AnganwadiCenter> anganwadiCenters;

    public Supervisor() {}
    public Supervisor(String name) { this.name = name; }

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

    public Cdpo getCdpo() {
        return cdpo;
    }

    public void setCdpo(Cdpo cdpo) {
        this.cdpo = cdpo;
    }

    public List<AnganwadiCenter> getAnganwadiCenters() {
        return anganwadiCenters;
    }

    public void setAnganwadiCenters(List<AnganwadiCenter> anganwadiCenters) {
        this.anganwadiCenters = anganwadiCenters;
    }
}
