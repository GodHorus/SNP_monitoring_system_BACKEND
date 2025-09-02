package com.example.master.Dto;
import java.math.BigDecimal;


public class IngredientDetailDTO {
    private Long id;
    private String type;
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;
    private String unit;
    private String vendor;
    private BigDecimal total;
    private String batchNo;
    private Long demandId;

    private BatchDetailDTO batchDetailDTO;
    private PackagingDetailDTO packagingDetailDTO;
    private DispatchDetailDTO dispatchDetailDTO;
    private LabReportDTO labReportDTO;
    public LabReportDTO getLabReportDTO() {
        return labReportDTO;
    }

    public void setLabReportDTO(LabReportDTO labReportDTO) {
        this.labReportDTO = labReportDTO;
    }

    public BatchDetailDTO getBatchDetailDTO() {
        return batchDetailDTO;
    }

    public void setBatchDetailDTO(BatchDetailDTO batchDetailDTO) {
        this.batchDetailDTO = batchDetailDTO;
    }

    public PackagingDetailDTO getPackagingDetailDTO() {
        return packagingDetailDTO;
    }

    public void setPackagingDetailDTO(PackagingDetailDTO packagingDetailDTO) {
        this.packagingDetailDTO = packagingDetailDTO;
    }

    public DispatchDetailDTO getDispatchDetailDTO() {
        return dispatchDetailDTO;
    }

    public void setDispatchDetailDTO(DispatchDetailDTO dispatchDetailDTO) {
        this.dispatchDetailDTO = dispatchDetailDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }
}
