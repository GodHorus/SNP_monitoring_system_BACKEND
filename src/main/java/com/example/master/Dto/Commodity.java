package com.example.master.Dto;

public enum Commodity {
    RICE(1L, "Rice"),
    FR(2L, "FR"),
    MILLETS(3L, "Millets"),
    WHEAT(4L, "Wheat");

    private final Long id;
    private final String name;

    Commodity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Utility: get enum by id
    public static Commodity getById(Long id) {
        for (Commodity c : values()) {
            if (c.id.equals(id)) {
                return c;
            }
        }
        return null;
    }

    // Utility: get enum by name
    public static Commodity getByName(String name) {
        for (Commodity c : values()) {
            if (c.name.equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}
