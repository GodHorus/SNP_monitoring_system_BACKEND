package com.example.master.event;

public class DemandDomainEvent {
    private final String payload;

    public DemandDomainEvent(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
