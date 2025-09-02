package com.example.master.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakUserService {

    @Value("${app.keycloak.server-url:http://localhost:8080}")
    private String serverUrl;

    @Value("${app.keycloak.realm:master}")
    private String realm;

    @Value("${app.keycloak.admin-username:admin}")
    private String adminUsername;

    @Value("${app.keycloak.admin-password:admin}")
    private String adminPassword;

    private Keycloak keycloak;

    private Keycloak getKeycloakInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm("master") // Use master realm for admin operations
                    .clientId("admin-cli")
                    .username(adminUsername)
                    .password(adminPassword)
                    .build();
        }
        return keycloak;
    }

    public List<String> getUserEmailsByRole(String roleName) {
        try {
            Keycloak keycloakInstance = getKeycloakInstance();
            RealmResource realmResource = keycloakInstance.realm(realm);
            RolesResource rolesResource = realmResource.roles();
            UsersResource usersResource = realmResource.users();

            // Get the role representation
            RoleRepresentation role = rolesResource.get(roleName).toRepresentation();

            if (role == null) {
                System.err.println("Role not found: " + roleName);
                return new ArrayList<>();
            }

            // Get users with this role
            List<UserRepresentation> usersWithRole = usersResource.list().stream()
                    .filter(user -> {
                        try {
                            return usersResource.get(user.getId()).roles().realmLevel().listAll()
                                    .stream()
                                    .anyMatch(userRole -> userRole.getName().equals(roleName));
                        } catch (Exception e) {
                            System.err.println("Error checking roles for user: " + user.getUsername());
                            return false;
                        }
                    })
                    .collect(Collectors.toList());

            // Extract emails
            return usersWithRole.stream()
                    .filter(user -> user.getEmail() != null && !user.getEmail().isEmpty())
                    .map(UserRepresentation::getEmail)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Error fetching users for role " + roleName + ": " + e.getMessage());

            // Fallback: Return hardcoded emails for development/testing
            return getHardcodedEmailsByRole(roleName);
        }
    }

    /**
     * Fallback method with hardcoded emails based on your login details
     */
    private List<String> getHardcodedEmailsByRole(String roleName) {
        return switch (roleName.toUpperCase()) {
            case "ADMIN" -> List.of("snp@gmail.com");
            case "DWCD" -> List.of("18pixels@gmail.com");
            case "FCI" -> List.of("virendra@18pixels.com");
            case "SUPPLIER" -> List.of("virendrauim.90@gmail.com");
            case "CDPO" -> List.of("18pixelslko@gmail.com");
            case "AWC" -> List.of("mohdwxyz@gmail.com");
            default -> new ArrayList<>();
        };
    }

    /**
     * Get user details by email
     */
    public UserRepresentation getUserByEmail(String email) {
        try {
            Keycloak keycloakInstance = getKeycloakInstance();
            RealmResource realmResource = keycloakInstance.realm(realm);
            UsersResource usersResource = realmResource.users();

            List<UserRepresentation> users = usersResource.search(null, null, null, email, 0, 1);

            return users.isEmpty() ? null : users.get(0);
        } catch (Exception e) {
            System.err.println("Error fetching user by email: " + e.getMessage());
            return null;
        }
    }

    /**
     * Check if user has specific role
     */
    public boolean userHasRole(String userId, String roleName) {
        try {
            Keycloak keycloakInstance = getKeycloakInstance();
            RealmResource realmResource = keycloakInstance.realm(realm);
            UsersResource usersResource = realmResource.users();

            return usersResource.get(userId).roles().realmLevel().listAll()
                    .stream()
                    .anyMatch(role -> role.getName().equals(roleName));
        } catch (Exception e) {
            System.err.println("Error checking user role: " + e.getMessage());
            return false;
        }
    }


    public void sendResetPasswordEmail(String userId) {
        Keycloak keycloakInstance = getKeycloakInstance();
        RealmResource realmResource = keycloakInstance.realm(realm);
        UserResource userResource = realmResource.users().get(userId);

        // Keycloak will send reset email with link
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    /**
     * Reset password programmatically (direct API reset)
     */
    public void resetUserPassword(String userId, String newPassword) {
        Keycloak keycloakInstance = getKeycloakInstance();
        RealmResource realmResource = keycloakInstance.realm(realm);
        UserResource userResource = realmResource.users().get(userId);

        CredentialRepresentation newCred = new CredentialRepresentation();
        newCred.setTemporary(false); // true if you want user to reset again on next login
        newCred.setType(CredentialRepresentation.PASSWORD);
        newCred.setValue(newPassword);

        userResource.resetPassword(newCred);
    }
}