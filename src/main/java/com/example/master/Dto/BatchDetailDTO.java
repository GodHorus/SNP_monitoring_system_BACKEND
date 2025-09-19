package com.example.master.Dto;


public class BatchDetailDTO {
    public Long demandId;
    public String type;
    public Long quantity;
    public String qrCode;

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}


//import java.util.List;
//
//public class BatchDetailDTO {
//    private Long id;
//    private String qrCode;
//    private Long labReportId;
//
//    private Long totalQuantity;
//
//    // ✅ Instead of single ingredientId → list of ingredients
//    private List<IngredientDetailDTO> ingredients;
//    private LabReportDTO labReport;
//
//    private String batchNo;
//
//    // --- Getters & Setters ---
//
//
//    public Long getTotalQuantity() {
//        return totalQuantity;
//    }
//
//    public void setTotalQuantity(Long totalQuantity) {
//        this.totalQuantity = totalQuantity;
//    }
//
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getQrCode() { return qrCode; }
//    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
//
//    public Long getLabReportId() { return labReportId; }
//    public void setLabReportId(Long labReportId) { this.labReportId = labReportId; }
//
//    public List<IngredientDetailDTO> getIngredients() { return ingredients; }
//    public void setIngredients(List<IngredientDetailDTO> ingredients) { this.ingredients = ingredients; }
//
//    public LabReportDTO getLabReport() { return labReport; }
//    public void setLabReport(LabReportDTO labReport) { this.labReport = labReport; }
//
//    public String getBatchNo() {
//        return batchNo;
//    }
//
//    public void setBatchNo(String batchNo) {
//        this.batchNo = batchNo;
//    }
//}
