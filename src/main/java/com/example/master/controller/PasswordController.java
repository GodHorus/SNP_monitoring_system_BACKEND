package com.example.master.controller;

import com.example.master.config.KeycloakUserService;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class PasswordController {

    private final KeycloakUserService keycloakUserService;

    public PasswordController(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    /**
     * Forgot password - trigger reset email
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");

        // Check if email is missing or blank
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        UserRepresentation user = keycloakUserService.getUserByEmail(email);

        // Check if the user exists
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with email: " + email);
        }

        try {
            // Send reset password email
            keycloakUserService.sendResetPasswordEmail(user.getId());
            return ResponseEntity.ok("Password reset email sent successfully to " + email);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }


    /**
     * Reset password - API-driven (if you don’t want to rely on Keycloak email link)
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email,
                                                @RequestParam String newPassword) {
        UserRepresentation user = keycloakUserService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with email: " + email);
        }

        try {
            keycloakUserService.resetUserPassword(user.getId(), newPassword);
            return ResponseEntity.ok("Password reset successfully for user: " + email);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
