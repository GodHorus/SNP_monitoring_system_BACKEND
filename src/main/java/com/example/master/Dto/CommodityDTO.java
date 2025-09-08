package com.example.master.Dto;

public class CommodityDTO {

    private Long id;
    private String name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Constructor
    public CommodityDTO() {
    }

    public CommodityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
