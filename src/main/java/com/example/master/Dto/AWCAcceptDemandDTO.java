package com.example.master.Dto;

public class AWCAcceptDemandDTO {

    private Long id;
    private Integer recievednumber;
    private String remarks;
    private Long dispatchId; // reference to DispatchDetail

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRecievednumber() {
        return recievednumber;
    }

    public void setRecievednumber(Integer recievednumber) {
        this.recievednumber = recievednumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Long dispatchId) {
        this.dispatchId = dispatchId;
    }
}
