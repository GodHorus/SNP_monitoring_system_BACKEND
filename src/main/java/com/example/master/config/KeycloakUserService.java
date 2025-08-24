package com.example.master.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakUserService {


    private final Keycloak keycloak;
    private final String realm = "snp-assam"; // change to your Keycloak realm name if you using different onwee

    public KeycloakUserService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public List<String> getUserEmailsByRole(String roleName) {
        RealmResource realmResource = keycloak.realm(realm);
        List<UserRepresentation> users = realmResource.users().list();

        return users.stream()
                .filter(user -> user.getRealmRoles().contains(roleName))
                .map(UserRepresentation::getEmail)
                .collect(Collectors.toList());
    }
}
