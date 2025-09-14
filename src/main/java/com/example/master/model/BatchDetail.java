package com.example.master.model;

import com.example.master.model.IngredientDetail;
import com.example.master.model.LabReport;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "batch_details")
public class BatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "batchDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientDetail> ingredients = new ArrayList<>();

    @Column(name = "qr_code", length = 1024)
    private String qrCode;

    private Long totalQuantity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lab_report_id")
    private LabReport labReport;

    @OneToMany(mappedBy = "batchDetail", cascade = CascadeType.ALL)
    private List<PackagingDetail> packagingDetails = new ArrayList<>();

    // --- getters/setters ---


    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IngredientDetail> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDetail> ingredients) {
        this.ingredients = ingredients;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LabReport getLabReport() {
        return labReport;
    }

    public void setLabReport(LabReport labReport) {
        this.labReport = labReport;
    }

    public List<PackagingDetail> getPackagingDetails() {
        return packagingDetails;
    }

    public void setPackagingDetails(List<PackagingDetail> packagingDetails) {
        this.packagingDetails = packagingDetails;
    }
}
