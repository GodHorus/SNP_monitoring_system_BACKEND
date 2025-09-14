package com.example.master.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "awc_dispatch")
public class AWCDispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anganwadi_id", nullable = false) // foreign key to AnganwadiCenter
    private AnganwadiCenter anganwadiCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demand_id", nullable = false)
    private Demand demand;

    private String sublotNo;
    private Integer benficiaryCount;
    private Integer distributedPackets;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnganwadiCenter getAnganwadiCenter() {
        return anganwadiCenter;
    }

    public void setAnganwadiCenter(AnganwadiCenter anganwadiCenter) {
        this.anganwadiCenter = anganwadiCenter;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public String getSublotNo() {
        return sublotNo;
    }

    public void setSublotNo(String sublotNo) {
        this.sublotNo = sublotNo;
    }

    public Integer getBenficiaryCount() {
        return benficiaryCount;
    }

    public void setBenficiaryCount(Integer benficiaryCount) {
        this.benficiaryCount = benficiaryCount;
    }

    public Integer getDistributedPackets() {
        return distributedPackets;
    }

    public void setDistributedPackets(Integer distributedPackets) {
        this.distributedPackets = distributedPackets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
