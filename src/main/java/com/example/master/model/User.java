package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // store Keycloak User ID (UUID from Keycloak)
    @Column(nullable = false, unique = true)
    private String keycloakUserId;

    private String name;
    private String email;
    private String mobile;
    private String district;
//    private String project;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKeycloakUserId() { return keycloakUserId; }
    public void setKeycloakUserId(String keycloakUserId) { this.keycloakUserId = keycloakUserId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

//    public String getProject() { return project; }
//    public void setProject(String project) { this.project = project; }
}
