package com.example.master.Dto;

public class WarehouseDTO {

    private Long id;
    private String warehouseName;
    private String location;

    // Constructors
    public WarehouseDTO(Long id, String warehouseName, String location) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
