package com.example.master.repository;

import com.example.master.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByRoleId(Long roleId);
    void deleteByRoleId(Long roleId);
}
