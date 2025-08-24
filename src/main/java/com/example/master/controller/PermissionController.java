package com.example.master.controller;

import com.example.master.model.Permission;
import com.example.master.repository.PermissionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
//@CrossOrigin(origins = "*")
public class PermissionController {

    private final PermissionRepository repo;

    public PermissionController(PermissionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<List<Permission>> getPermissions(@PathVariable Long roleId) {
        return ResponseEntity.ok(repo.findByRoleId(roleId));
    }

    @PostMapping("/{roleId}")
    public ResponseEntity<List<Permission>> savePermissions(
            @PathVariable Long roleId,
            @RequestBody List<Permission> permissions) {
        repo.deleteByRoleId(roleId); // clear old
        for (Permission p : permissions) {
            p.setRoleId(roleId);
        }
        return ResponseEntity.ok(repo.saveAll(permissions));
    }
}
