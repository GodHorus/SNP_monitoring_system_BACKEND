package com.example.master.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    // Handle preflight requests
//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
//    public ResponseEntity<?> handleOptions() {
//        return ResponseEntity.ok()
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
//                .header("Access-Control-Max-Age", "3600")
//                .build();
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("=== LOGIN REQUEST RECEIVED ===");
        System.out.println("Username: " + username);
        System.out.println("Timestamp: " + new Date());

        String tokenUrl = keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        System.out.println("Token URL: " + tokenUrl);
        System.out.println("Client ID: " + clientId);
        System.out.println("Realm: " + realm);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("username", username);
        params.add("password", password);
        params.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            System.out.println("Sending request to Keycloak...");
            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);
            System.out.println("Keycloak response received successfully");

            // Return response with CORS headers
            return ResponseEntity.ok()
//                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Credentials", "true")
                    .body(response.getBody());

        } catch (HttpClientErrorException e) {
            System.err.println("=== KEYCLOAK ERROR ===");
            System.err.println("HTTP Status: " + e.getStatusCode());
            System.err.println("Response Body: " + e.getResponseBodyAsString());
            System.err.println("Headers: " + e.getResponseHeaders());

            return ResponseEntity.status(e.getStatusCode())
//                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Credentials", "true")
                    .body(Map.of(
                            "error", "Authentication failed",
                            "details", e.getResponseBodyAsString(),
                            "status", e.getStatusCode().value()
                    ));
        } catch (Exception e) {
            System.err.println("=== GENERAL ERROR ===");
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Credentials", "true")
                    .body(Map.of("error", "Internal server error", "message", e.getMessage()));
        }
    }
}