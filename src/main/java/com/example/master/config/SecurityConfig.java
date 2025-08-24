package com.example.master.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.core.convert.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // This is crucial for @PreAuthorize to work
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/demands/**").authenticated()
                        .requestMatchers("/actuator/health").permitAll() // Health check endpoint
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return converter;
    }

    /**
     * Enhanced converter with detailed logging for debugging
     */
    public static class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        private static final Logger logger = LoggerFactory.getLogger(KeycloakRoleConverter.class);

        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Set<String> roles = new HashSet<>();

            // Log the entire JWT token for debugging (remove in production)
            logger.debug("=== JWT TOKEN DEBUG ===");
            logger.debug("JWT Subject: {}", jwt.getSubject());
            logger.debug("JWT Claims: {}", jwt.getClaims());

            // Extract username/email for logging
            String username = jwt.getClaimAsString("preferred_username");
            String email = jwt.getClaimAsString("email");
            logger.info("Processing JWT for user: {} ({})", username, email);

            // Realm roles
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                Collection<String> realmRoles = (Collection<String>) realmAccess.get("roles");
                logger.info("Realm roles found: {}", realmRoles);
                roles.addAll(realmRoles);
            } else {
                logger.warn("No realm_access found in JWT token");
            }

            // Client roles (resource_access)
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess != null) {
                logger.info("Resource access found with clients: {}", resourceAccess.keySet());
                resourceAccess.forEach((client, clientData) -> {
                    logger.info("Processing client: {}", client);
                    Map<String, Object> clientMap = (Map<String, Object>) clientData;
                    if (clientMap.containsKey("roles")) {
                        Collection<String> clientRoles = (Collection<String>) clientMap.get("roles");
                        logger.info("Client {} roles: {}", client, clientRoles);
                        roles.addAll(clientRoles);
                    }
                });
            } else {
                logger.warn("No resource_access found in JWT token");
            }

            // Check for roles in other possible locations
            checkAlternativeRoleLocations(jwt, roles);

            // Final roles extraction
            Collection<GrantedAuthority> authorities = roles.stream()
                    .map(role -> {
                        String authority = "ROLE_" + role.toUpperCase();
                        logger.info("Creating authority: {}", authority);
                        return new SimpleGrantedAuthority(authority);
                    })
                    .collect(Collectors.toSet());

            logger.info("=== FINAL AUTHORITIES FOR USER {} ===", username);
            authorities.forEach(auth -> logger.info("Authority: {}", auth.getAuthority()));
            logger.info("=== END AUTHORITIES ===");

            // Validation check
            if (authorities.isEmpty()) {
                logger.error("NO AUTHORITIES FOUND! This will cause authorization issues.");
                logger.error("JWT Claims dump: {}", jwt.getClaims());
            }

            return authorities;
        }

        private void checkAlternativeRoleLocations(Jwt jwt, Set<String> roles) {
            // Check if roles are directly in claims
            Object directRoles = jwt.getClaim("roles");
            if (directRoles instanceof Collection) {
                Collection<String> roleList = (Collection<String>) directRoles;
                logger.info("Direct roles found: {}", roleList);
                roles.addAll(roleList);
            }

            // Check groups (sometimes used instead of roles)
            Object groups = jwt.getClaim("groups");
            if (groups instanceof Collection) {
                Collection<String> groupList = (Collection<String>) groups;
                logger.info("Groups found (treating as roles): {}", groupList);
                roles.addAll(groupList);
            }

            // Check custom claims
            Object customRoles = jwt.getClaim("custom_roles");
            if (customRoles instanceof Collection) {
                Collection<String> customRoleList = (Collection<String>) customRoles;
                logger.info("Custom roles found: {}", customRoleList);
                roles.addAll(customRoleList);
            }
        }
    }
}