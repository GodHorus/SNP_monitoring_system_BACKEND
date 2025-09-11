package com.example.master.controller;

import com.example.master.Dto.UserRequestDTO;
import com.example.master.model.User;
import com.example.master.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setDistrict(request.getDistrict());
        user.setCdpo(request.getCdpo());
        user.setSectors(request.getSectors());

        // Call service including firstName & lastName for Keycloak
        User savedUser = userService.createUser(
                user,
                request.getPassword(),
                request.getRole(),
                request.getFirstName(),
                request.getLastName()
        );

        return ResponseEntity.ok(savedUser);
    }


    // ✅ Update User Role
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/role")
    public ResponseEntity<String> updateUserRole(
            @PathVariable String id,
            @RequestBody UpdateRoleRequest request) {
        userService.updateUserRole(id, request.getOldRole(), request.getNewRole());
        return ResponseEntity.ok("Role updated successfully");
    }

    // ✅ Get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ✅ Get user by email
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

class UpdateRoleRequest {
    private String oldRole;
    private String newRole;

    // getters and setters
    public String getOldRole() {
        return oldRole;
    }

    public void setOldRole(String oldRole) {
        this.oldRole = oldRole;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
