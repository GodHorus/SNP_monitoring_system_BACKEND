package com.example.master.Dto;

public class SupplierDTO {

    private Long id;
    private String name;
    private String email;
    private String keycloakUserId;

    private String password;
    private String role;
    private String firstName;
    private String lastName;

    // Constructors
    public SupplierDTO() {}

    public SupplierDTO(Long id, String name, String email, String keycloakUserId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.keycloakUserId = keycloakUserId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getKeycloakUserId() {
        return keycloakUserId;
    }

    public void setKeycloakUserId(String keycloakUserId) {
        this.keycloakUserId = keycloakUserId;
    }
}

