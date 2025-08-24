package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roleId;  // link to Role
    private String module; // e.g., "users"
    private String action; // create | view | update | delete

    public Permission() {}
    public Permission(Long roleId, String module, String action) {
        this.roleId = roleId;
        this.module = module;
        this.action = action;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}
