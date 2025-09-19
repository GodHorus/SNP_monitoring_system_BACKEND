package com.example.master.Dto;

public class UserProfileDTO {
    private String name;
    private String email; // non-editable
    private String mobile;
    private String district;
    private String cdpo;
    private String sectors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCdpo() {
        return cdpo;
    }

    public void setCdpo(String cdpo) {
        this.cdpo = cdpo;
    }

    public String getSectors() {
        return sectors;
    }

    public void setSectors(String sectors) {
        this.sectors = sectors;
    }
}