package com.example.master.Dto;

import java.time.LocalDateTime;

public class PacketsRemarksDTO {
    private Long id;
    private Long awcDispatchId;
    private String remark;
    private LocalDateTime createdAt;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getAwcDispatchId() {
        return awcDispatchId;
    }
    public void setAwcDispatchId(Long awcDispatchId) {
        this.awcDispatchId = awcDispatchId;
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
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
