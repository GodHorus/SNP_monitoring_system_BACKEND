package com.example.master.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "packets_remarks")
public class PacketsRemarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many remarks belong to one AWCDispatch
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awc_dispatch_id", nullable = false)  // foreign key
    private AWCDispatch awcDispatch;

    private String remark;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public AWCDispatch getAwcDispatch() {
        return awcDispatch;
    }

    public void setAwcDispatch(AWCDispatch awcDispatch) {
        this.awcDispatch = awcDispatch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
