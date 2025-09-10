package com.example.master.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "packaging_details")
public class PackagingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    private Double availableStock;
    private Double packetSize;
    private String unit;
    private Integer packets;
    private Double remainingStock;

    // Relation with BatchDetail
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    private BatchDetail batchDetail;

//    @OneToOne(mappedBy = "packagingDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private DispatchDetail dispatchDetail;

    @OneToMany(mappedBy = "packagingDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DispatchDetail> dispatchDetails = new ArrayList<>();

//    public DispatchDetail getDispatchDetail() {
//        return dispatchDetail;
//    }
//
//    public void setDispatchDetail(DispatchDetail dispatchDetail) {
//        this.dispatchDetail = dispatchDetail;
//    }

    public List<DispatchDetail> getDispatchDetails() {
        return dispatchDetails;
    }

    public void setDispatchDetails(List<DispatchDetail> dispatchDetails) {
        this.dispatchDetails = dispatchDetails;
    }
    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Double getAvailableStock() { return availableStock; }
//    public void setAvailableStock(Double availableStock) { this.availableStock = availableStock; }

    public Double getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(Double packetSize) {
        this.packetSize = packetSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPackets() {
        return packets;
    }

    public void setPackets(Integer packets) {
        this.packets = packets;
    }

    public Double getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(Double remainingStock) {
        this.remainingStock = remainingStock;
    }

    public BatchDetail getBatchDetail() {
        return batchDetail;
    }

    public void setBatchDetail(BatchDetail batchDetail) {
        this.batchDetail = batchDetail;
    }
}
