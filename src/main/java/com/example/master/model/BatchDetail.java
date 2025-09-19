// BatchDetail.java
package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "batch_details")
public class BatchDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demand_id", nullable = false)
    private Long demandId;

    private String type;

    @Column(name = "batch_number", unique = true)
    private String batchNumber; // e.g. B-1

    private Long quantity;

    @Column(name = "qr_code", length = 1024)
    private String qrCode;

    public BatchDetail(){}

    public BatchDetail(Long demandId, String type, Long quantity, String qrCode) {
        this.demandId = demandId; this.type = type; this.quantity = quantity; this.qrCode = qrCode;
    }
    // getters/setters...
    public Long getId(){ return id; }
    public Long getDemandId(){ return demandId; }
    public void setDemandId(Long demandId){ this.demandId = demandId; }
    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }
    public String getBatchNumber(){ return batchNumber; }
    public void setBatchNumber(String batchNumber){ this.batchNumber = batchNumber; }
    public Long getQuantity(){ return quantity; }
    public void setQuantity(Long quantity){ this.quantity = quantity; }
    public String getQrCode(){ return qrCode; }
    public void setQrCode(String qrCode){ this.qrCode = qrCode; }
}













//package com.example.master.model;
//
//import com.example.master.model.IngredientDetail;
//import com.example.master.model.LabReport;
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.List;
//@Entity
//@Table(name = "batch_details")
//public class BatchDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToMany(mappedBy = "batchDetail", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<IngredientDetail> ingredients = new ArrayList<>();
//
//    @Column(name = "qr_code", length = 1024)
//    private String qrCode;
//
//    private Long totalQuantity;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "lab_report_id")
//    private LabReport labReport;
//
//    @OneToMany(mappedBy = "batchDetail", cascade = CascadeType.ALL)
//    private List<PackagingDetail> packagingDetails = new ArrayList<>();
//
//    // --- getters/setters ---
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
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<IngredientDetail> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<IngredientDetail> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public String getQrCode() {
//        return qrCode;
//    }
//
//    public void setQrCode(String qrCode) {
//        this.qrCode = qrCode;
//    }
//
//    public LabReport getLabReport() {
//        return labReport;
//    }
//
//    public void setLabReport(LabReport labReport) {
//        this.labReport = labReport;
//    }
//
//    public List<PackagingDetail> getPackagingDetails() {
//        return packagingDetails;
//    }
//
//    public void setPackagingDetails(List<PackagingDetail> packagingDetails) {
//        this.packagingDetails = packagingDetails;
//    }
//}
