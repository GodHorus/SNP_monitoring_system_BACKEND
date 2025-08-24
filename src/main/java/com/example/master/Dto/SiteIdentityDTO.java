package com.example.master.Dto;

public class SiteIdentityDTO {
    private Long id;
    private String address;
    private String headerName;
    private String siteLogo;
    private String loginSiteLogo;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getHeaderName() { return headerName; }
    public void setHeaderName(String headerName) { this.headerName = headerName; }

    public String getSiteLogo() { return siteLogo; }
    public void setSiteLogo(String siteLogo) { this.siteLogo = siteLogo; }

    public String getLoginSiteLogo() { return loginSiteLogo; }
    public void setLoginSiteLogo(String loginSiteLogo) { this.loginSiteLogo = loginSiteLogo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
