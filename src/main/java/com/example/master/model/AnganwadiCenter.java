package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anganwadi_centers")
public class AnganwadiCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "center_id", nullable = false, unique = true)
    private String centerId;

    @Column(name = "center_name", nullable = false)
    private String centerName;

    @Column(name = "center_address")
    private String centerAddress;

    @Column(name = "status", nullable = false)
    private String status; // active/inactive

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id")
    private Block block;

    public AnganwadiCenter() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCenterId() { return centerId; }
    public void setCenterId(String centerId) { this.centerId = centerId; }

    public String getCenterName() { return centerName; }
    public void setCenterName(String centerName) { this.centerName = centerName; }

    public String getCenterAddress() { return centerAddress; }
    public void setCenterAddress(String centerAddress) { this.centerAddress = centerAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Supervisor getSupervisor() { return supervisor; }
    public void setSupervisor(Supervisor supervisor) { this.supervisor = supervisor; }

    public District getDistrict() { return district; }
    public void setDistrict(District district) { this.district = district; }

    public Block getBlock() { return block; }
    public void setBlock(Block block) { this.block = block; }
}
