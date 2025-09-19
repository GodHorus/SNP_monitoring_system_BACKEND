package com.example.master.Dto;

import java.util.List;

public class PackagingDetailDTO {

    public Long demandId;
    public String batchNumber;
    public String type;
    public Double availableStock;
    public Double packetSize;
    public String unit;
    public Integer packets;
    public Double remainingStock;

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Double availableStock) {
        this.availableStock = availableStock;
    }

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

    //    private Long id;
////    private Double availableStock;
//    private Double packetSize;
//    private String unit;
//    private Integer packets;
//    private Double remainingStock;
//    private Long batchId;
//
//    private BatchDetailDTO batchDetailDTO;
//
//    private List<DispatchDetailDTO> dispatchDetailDTOs;
//
//    // Getters and Setters
//    public List<DispatchDetailDTO> getDispatchDetailDTOs() {
//        return dispatchDetailDTOs;
//    }
//
//    public void setDispatchDetailDTOs(List<DispatchDetailDTO> dispatchDetailDTOs) {
//        this.dispatchDetailDTOs = dispatchDetailDTOs;
//    }
//
//    public BatchDetailDTO getBatchDetailDTO() {
//        return batchDetailDTO;
//    }
//
//    public void setBatchDetailDTO(BatchDetailDTO batchDetailDTO) {
//        this.batchDetailDTO = batchDetailDTO;
//    }
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
////    public Double getAvailableStock() { return availableStock; }
////    public void setAvailableStock(Double availableStock) { this.availableStock = availableStock; }
//
//    public Double getPacketSize() { return packetSize; }
//    public void setPacketSize(Double packetSize) { this.packetSize = packetSize; }
//
//    public String getUnit() { return unit; }
//    public void setUnit(String unit) { this.unit = unit; }
//
//    public Integer getPackets() { return packets; }
//    public void setPackets(Integer packets) { this.packets = packets; }
//
//    public Double getRemainingStock() { return remainingStock; }
//    public void setRemainingStock(Double remainingStock) { this.remainingStock = remainingStock; }
//
//    public Long getBatchId() { return batchId; }
//    public void setBatchId(Long batchId) { this.batchId = batchId; }
}
