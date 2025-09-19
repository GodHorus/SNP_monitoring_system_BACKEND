package com.example.master.config;

import com.example.master.Dto.UserRequestDTO;
import jakarta.ws.rs.core.Response;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KeycloakUserService {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin-username:admin}")
    private String adminUsername;

    @Value("${keycloak.admin-password:admin}")
    private String adminPassword;

    private Keycloak keycloak;

    private Keycloak getKeycloakInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm("realm") // Use master realm for admin operations
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

    //    h handeling the users in the keycloak userserservice
    public String createUser(String username, String email, String password, String roleName, String firstName, String lastName) {
        Keycloak keycloakInstance = getKeycloakInstance();
        RealmResource realmResource = keycloakInstance.realm(realm);
        UsersResource usersResource = realmResource.users();

        // Create Keycloak user
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);
        user.setFirstName(firstName);  // set first name
        user.setLastName(lastName);    // set last name
        user.setEmailVerified(true);

        Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create user: " + response.getStatus());
        }

        // Get created user ID
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Set password
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        usersResource.get(userId).resetPassword(credential);

        // Assign role
        RoleRepresentation role = realmResource.roles().get(roleName).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(List.of(role));

        return userId;
    }


//     this si for updating th euser roles and manging theri roles

    public void updateUserRole(String userId, String oldRole, String newRole) {
        Keycloak keycloakInstance = getKeycloakInstance();
        RealmResource realmResource = keycloakInstance.realm(realm);
        UsersResource usersResource = realmResource.users();

        UserResource userResource = usersResource.get(userId);

        // Remove old role
        RoleRepresentation oldRoleRep = realmResource.roles().get(oldRole).toRepresentation();
        userResource.roles().realmLevel().remove(List.of(oldRoleRep));

        // Add new role
        RoleRepresentation newRoleRep = realmResource.roles().get(newRole).toRepresentation();
        userResource.roles().realmLevel().add(List.of(newRoleRep));
    }

    public String getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new IllegalStateException("No authenticated user found");
        }
        return auth.getName(); // usually the Keycloak userId (UUID)
    }
    public void updateUserPassword(String userId, String newPassword) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newPassword);

        keycloak.realm("realm")
                .users()
                .get(userId)
                .resetPassword(credential);
    }



    public Set<String> getCurrentUserRolesFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof KeycloakPrincipal)) {
            throw new IllegalStateException("Cannot extract roles – no authenticated Keycloak user found.");
        }

        KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
        AccessToken token = principal.getKeycloakSecurityContext().getToken();

        return token.getRealmAccess().getRoles(); // Returns a Set<String> of roles
    }


}