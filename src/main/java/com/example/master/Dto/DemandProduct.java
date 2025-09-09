package com.example.master.Dto;

public enum DemandProduct {
    KHICHDI(1L, "Khichdi"),
    SUJI_HALWA(2L, "Suji Halwa");

    private final Long id;
    private final String name;

    DemandProduct(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Utility: find enum by id
    public static DemandProduct getById(Long id) {
        for (DemandProduct dp : values()) {
            if (dp.id.equals(id)) {
                return dp;
            }
        }
        return null;
    }

    // Utility: find enum by name
    public static DemandProduct getByName(String name) {
        for (DemandProduct dp : values()) {
            if (dp.name.equalsIgnoreCase(name)) {
                return dp;
            }
        }
        return null;
    }
}
