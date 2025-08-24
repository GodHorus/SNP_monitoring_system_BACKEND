package com.example.master.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Extracts roles from Keycloak JWT:
 * - realm roles from `realm_access.roles`
 * - client roles from `resource_access.{clientId}.roles`
 * and maps them to Spring Security format: `ROLE_<ROLE_NAME>`
 */
public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final String clientId;

    public KeycloakRealmRoleConverter(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Set<String> roles = new HashSet<>();

        // Realm roles
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            Object realmRoles = realmAccess.get("roles");
            if (realmRoles instanceof Collection<?> rr) {
                rr.forEach(r -> roles.add(String.valueOf(r)));
            }
        }

        // Client roles
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            Object client = resourceAccess.get(clientId);
            if (client instanceof Map<?, ?> clientMap) {
                Object clientRoles = ((Map<?, ?>) clientMap).get("roles");
                if (clientRoles instanceof Collection<?> cr) {
                    cr.forEach(r -> roles.add(String.valueOf(r)));
                }
            }
        }

        // Map to Spring Security SimpleGrantedAuthority
        return roles.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)     // normalize
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
