package com.example.master.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    // Public
    @GetMapping("/api/public/info")
    public String publicInfo() {
        return "Admin service is up ";
    }


//    testing on web not working  ==========
    @GetMapping("/api/auth")
    public String home(OAuth2AuthenticationToken token){
        String email = token.getPrincipal().getAttribute("email");
        return "Welcome " +email+" ";
    }

    // Secured endpoint: only ROLE_ADMIN
    @GetMapping("/api/admin/dashboard")
    public String dashboard(@AuthenticationPrincipal Jwt jwt) {
        String user = jwt.getClaimAsString("preferred_username");
        return "Welcome Admin " + user ;
    }

    // Authenticated endpoint (any authenticated role)
    @GetMapping("/api/me")
    public Object me(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }
}
