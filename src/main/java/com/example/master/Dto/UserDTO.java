package com.example.master.Dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String role;
    private String district;
    private String project;

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String mobile, String password,
                   String role, String district, String project) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.district = district;
        this.project = project;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }
}
