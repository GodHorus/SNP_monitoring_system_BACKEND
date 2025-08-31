package com.example.master.model;

import com.example.master.model.IngredientDetail;
import com.example.master.model.LabReport;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "batch_details")
public class BatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Relation with IngredientDetail (foreign key)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private IngredientDetail ingredient;

    private String qrCode;

    // ✅ Relation with LabReport
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lab_report_id")
    private LabReport labReport;

    @OneToMany(mappedBy = "batchDetail", cascade = CascadeType.ALL)
    private List<PackagingDetail> packagingDetails;

    public List<PackagingDetail> getPackagingDetails() {
        return packagingDetails;
    }

    public void setPackagingDetails(List<PackagingDetail> packagingDetails) {
        this.packagingDetails = packagingDetails;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public IngredientDetail getIngredient() { return ingredient; }
    public void setIngredient(IngredientDetail ingredient) { this.ingredient = ingredient; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public LabReport getLabReport() { return labReport; }
    public void setLabReport(LabReport labReport) { this.labReport = labReport; }

}
