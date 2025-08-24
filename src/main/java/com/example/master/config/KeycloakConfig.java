package com.example.master.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    private final String serverUrl = "http://localhost:8080"; // Keycloak base URL
    private final String realm = "snp-assam";                 // Your realm
    private final String clientId = "snp-backend";            // Client ID
    private final String clientSecret = "Fck3Uluq4hDzg4Z4sLPMJv4cYP9itiDV"; // Client Secret
    private final String username = "admin";                  // Keycloak admin username
    private final String password = "admin";                  // Keycloak admin password

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl + "/auth")
                .realm("master")
                .grantType(OAuth2Constants.PASSWORD)
                .clientId("admin-cli")
                .username(username)
                .password(password)
                .build();
    }
}
