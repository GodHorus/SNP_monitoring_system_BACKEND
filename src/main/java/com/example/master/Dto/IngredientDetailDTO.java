package com.example.master.Dto;

import java.math.BigDecimal;
import java.util.List;

public class IngredientDetailDTO {
    private Long id;
    private String type;
    private String name;
    private BigDecimal quantity;
    private String unit;
    private BigDecimal total;
    private String batchNo;
    private Long demandId;
    private Long batchId;
    private BatchDetailDTO batchDetailDTO;
    private DispatchDetailDTO dispatchDetailDTO;
    private LabReportDTO labReportDTO;

    private List<PackagingDetailDTO> packagingDetailDTOs;

    // Getters and Setters
    public List<PackagingDetailDTO> getPackagingDetailDTOs() {
        return packagingDetailDTOs;
    }

    public void setPackagingDetailDTOs(List<PackagingDetailDTO> packagingDetailDTOs) {
        this.packagingDetailDTOs = packagingDetailDTOs;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }

    public Long getDemandId() { return demandId; }
    public void setDemandId(Long demandId) { this.demandId = demandId; }

    public Long getBatchId() { return batchId; }
    public void setBatchId(Long batchId) { this.batchId = batchId; }

    public BatchDetailDTO getBatchDetailDTO() { return batchDetailDTO; }
    public void setBatchDetailDTO(BatchDetailDTO batchDetailDTO) { this.batchDetailDTO = batchDetailDTO; }

//    public PackagingDetailDTO getPackagingDetailDTO() { return packagingDetailDTO; }
//    public void setPackagingDetailDTO(PackagingDetailDTO packagingDetailDTO) { this.packagingDetailDTO = packagingDetailDTO; }

    public DispatchDetailDTO getDispatchDetailDTO() { return dispatchDetailDTO; }
    public void setDispatchDetailDTO(DispatchDetailDTO dispatchDetailDTO) { this.dispatchDetailDTO = dispatchDetailDTO; }

    public LabReportDTO getLabReportDTO() { return labReportDTO; }
    public void setLabReportDTO(LabReportDTO labReportDTO) { this.labReportDTO = labReportDTO; }
}
