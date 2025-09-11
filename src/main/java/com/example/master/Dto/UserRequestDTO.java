package com.example.master.Dto;

public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String role;
    private String mobile;
    private String district;

    private String firstName;
    private String lastName;

    // ✅ new fields
    private String cdpo;
    private String sectors;

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCdpo() { return cdpo; }
    public void setCdpo(String cdpo) { this.cdpo = cdpo; }

    public String getSectors() { return sectors; }
    public void setSectors(String sectors) { this.sectors = sectors; }
}
