package com.example.master.Dto;

public class BatchDetailDTO {
    private Long id;
    private Long ingredientId;
    private String qrCode;
    private Long labReportId;

    // Nested DTOs for response
    private IngredientDetailDTO ingredient;
    private LabReportDTO labReport;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIngredientId() { return ingredientId; }
    public void setIngredientId(Long ingredientId) { this.ingredientId = ingredientId; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Long getLabReportId() { return labReportId; }
    public void setLabReportId(Long labReportId) { this.labReportId = labReportId; }

    public IngredientDetailDTO getIngredient() { return ingredient; }
    public void setIngredient(IngredientDetailDTO ingredient) { this.ingredient = ingredient; }

    public LabReportDTO getLabReport() { return labReport; }
    public void setLabReport(LabReportDTO labReport) { this.labReport = labReport; }
}
