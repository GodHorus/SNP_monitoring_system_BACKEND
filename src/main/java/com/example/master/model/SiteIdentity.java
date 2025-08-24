package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "site_identity")
public class SiteIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "header_name", nullable = false)
    private String headerName;

    @Column(name = "site_logo")
    private String siteLogo;

    @Column(name = "login_site_logo")
    private String loginSiteLogo;

    @Column(name = "status")
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
