package com.example.master.Dto;

public class DecisionRequest {
    private boolean accept;

    public DecisionRequest() {
    }

    public DecisionRequest(boolean accept) {
        this.accept = accept;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
