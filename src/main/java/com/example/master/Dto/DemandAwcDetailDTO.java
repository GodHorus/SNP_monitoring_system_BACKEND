package com.example.master.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

public class DemandAwcDetailDTO {

    @NotNull(message = "AWC ID is required")
    private Long awcId;

    @Positive(message = "Quantity must be positive")
    private int hcmNumber; // maps to quantity in demand

    @NotBlank(message = "Quantity unit is required")
    private String hcmUnit; // maps to quantityUnit

    // Getters and Setters
    public Long getAwcId() { return awcId; }
    public void setAwcId(Long awcId) { this.awcId = awcId; }

    public int getHcmNumber() { return hcmNumber; }
    public void setHcmNumber(int hcmNumber) { this.hcmNumber = hcmNumber; }

    public String getHcmUnit() { return hcmUnit; }
    public void setHcmUnit(String hcmUnit) { this.hcmUnit = hcmUnit; }
}
