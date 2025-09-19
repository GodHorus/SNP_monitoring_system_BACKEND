//package com.example.master.controller;
//
//import com.example.master.config.KeycloakUserService;
//import com.example.master.model.PasswordResetRequest;
//import com.example.master.model.PasswordUpdateRequest;
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth")
//public class PasswordController {
//
//    private final KeycloakUserService keycloakUserService;
//
//    public PasswordController(KeycloakUserService keycloakUserService) {
//        this.keycloakUserService = keycloakUserService;
//    }
//
//    /**
//     * Forgot password - trigger reset email
//     */
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
//        String email = payload.get("email");
//
//        // Check if email is missing or blank
//        if (email == null || email.isBlank()) {
//            return ResponseEntity.badRequest().body("Email is required");
//        }
//
//        UserRepresentation user = keycloakUserService.getUserByEmail(email);
//
//        // Check if the user exists
//        if (user == null) {
//            return ResponseEntity.badRequest().body("User not found with email: " + email);
//        }
//
//        try {
//            // Send reset password email
//            keycloakUserService.sendResetPasswordEmail(user.getId());
//            return ResponseEntity.ok("Password reset email sent successfully to " + email);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
//        }
//    }
//
//
//    /**
//     * Reset password - API-driven (if you don’t want to rely on Keycloak email link)
//     */
//    @PostMapping("/auth/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
//        String email = request.getEmail();
//        String newPassword = request.getNewPassword();
//
//        UserRepresentation user = keycloakUserService.getUserByEmail(email);
//
//        if (user == null) {
//            return ResponseEntity.badRequest().body("User not found with email: " + email);
//        }
//
//        try {
//            keycloakUserService.resetUserPassword(user.getId(), newPassword);
//            return ResponseEntity.ok("Password reset successfully for user: " + email);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
//        }
//    }
//
//
//    @PostMapping("/update-password")
//    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
//        String email = request.getEmail();
//        String oldPassword = request.getOldPassword();
//        String newPassword = request.getNewPassword();
//
//        if (email == null || email.isBlank() ||
//                oldPassword == null || oldPassword.isBlank() ||
//                newPassword == null || newPassword.isBlank()) {
//            return ResponseEntity.badRequest().body("Email, old password, and new password are required");
//        }
//
//        try {
//            // Authenticate user with old password
//            boolean isAuthenticated = keycloakUserService.verifyUserCredentials(email, oldPassword);
//
//            if (!isAuthenticated) {
//                return ResponseEntity.status(401).body("Invalid old password");
//            }
//
//            // Get user from Keycloak
//            var user = keycloakUserService.getUserByEmail(email);
//            if (user == null) {
//                return ResponseEntity.badRequest().body("User not found with email: " + email);
//            }
//
//            // Reset password to new one
//            keycloakUserService.resetUserPassword(user.getId(), newPassword);
//
//            return ResponseEntity.ok("Password updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
//        }
//    }
//
//
//}
