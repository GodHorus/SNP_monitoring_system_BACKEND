package com.example.master.controller;

import com.example.master.Dto.PasswordUpdateDTO;
import com.example.master.Dto.UserProfileDTO;
import com.example.master.services.UserService;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDTO> getProfile(JwtAuthenticationToken authentication) {
        String keycloakUserId = authentication.getToken().getSubject(); // This is the UUID (user ID)
        return ResponseEntity.ok(userService.getProfile(keycloakUserId));
    }



    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileDTO dto,
                                                JwtAuthenticationToken authentication) {
        String keycloakUserId = authentication.getToken().getSubject(); // "sub" claim in JWT
        userService.updateProfile(keycloakUserId, dto);
        return ResponseEntity.ok("Profile updated successfully");
    }


    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO dto,
                                                 @AuthenticationPrincipal Jwt jwt) {
        String keycloakUserId = jwt.getSubject();
        userService.updatePassword(keycloakUserId, dto);
        return ResponseEntity.ok("Password updated successfully");
    }

}
