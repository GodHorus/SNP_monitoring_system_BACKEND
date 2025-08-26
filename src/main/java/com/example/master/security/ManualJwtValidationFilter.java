package com.example.master.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Manual JWT Role Validation Filter
 * Acts as a backup security layer when Spring Security's @PreAuthorize fails.
 */
@Component
public class ManualJwtValidationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ManualJwtValidationFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        // Intercept DELETE requests to /api/demands/{id}
        if ("DELETE".equalsIgnoreCase(method) && requestURI.matches("/api/demands/\\d+")) {
            logger.info("Intercepted DELETE request to: {}", requestURI);

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.error("No valid Authorization header found");
                sendForbiddenResponse(response, "No valid authorization token");
                return;
            }

            String token = authHeader.substring(7);
            if (!isAdminUser(token)) {
                logger.error("Non-admin user attempted to delete demand");
                sendForbiddenResponse(response, "Admin role required for delete operations");
                return;
            }

            logger.info("Admin user verified, allowing delete operation");
        }

        filterChain.doFilter(request, response);
    }

    private boolean isAdminUser(String jwtToken) {
        try {
            String[] parts = jwtToken.split("\\.");
            if (parts.length != 3) {
                logger.error("Invalid JWT token format");
                return false;
            }

            // Decode JWT payload
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            logger.debug("JWT Payload: {}", payloadJson);

            JsonNode jsonNode = objectMapper.readTree(payloadJson);
            return checkAdminRole(jsonNode);

        } catch (Exception e) {
            logger.error("Error parsing JWT token", e);
            return false;
        }
    }

    private boolean checkAdminRole(JsonNode jsonNode) {
        // Check realm_access.roles
        JsonNode realmAccess = jsonNode.get("realm_access");
        if (realmAccess != null && realmAccess.get("roles") != null) {
            for (JsonNode role : realmAccess.get("roles")) {
                if ("ADMIN".equalsIgnoreCase(role.asText())) {
                    logger.info("Admin role found in realm_access.roles");
                    return true;
                }
            }
        }

        // Check resource_access for clients
        JsonNode resourceAccess = jsonNode.get("resource_access");
        if (resourceAccess != null) {
            for (JsonNode clientData : resourceAccess) {
                JsonNode roles = clientData.get("roles");
                if (roles != null) {
                    for (JsonNode role : roles) {
                        if ("ADMIN".equalsIgnoreCase(role.asText())) {
                            logger.info("Admin role found in resource_access roles");
                            return true;
                        }
                    }
                }
            }
        }

        // Check direct roles claim
        JsonNode directRoles = jsonNode.get("roles");
        if (directRoles != null) {
            for (JsonNode role : directRoles) {
                if ("ADMIN".equalsIgnoreCase(role.asText())) {
                    logger.info("Admin role found in direct roles claim");
                    return true;
                }
            }
        }

        // Check groups
        JsonNode groups = jsonNode.get("groups");
        if (groups != null) {
            for (JsonNode group : groups) {
                if ("ADMIN".equalsIgnoreCase(group.asText())) {
                    logger.info("Admin role found in groups claim");
                    return true;
                }
            }
        }

        // Check email fallback
        JsonNode email = jsonNode.get("email");
        if (email != null && "snp@gmail.com".equalsIgnoreCase(email.asText())) {
            logger.info("Admin role granted based on email: {}", email.asText());
            return true;
        }

        logger.warn("No admin role found for user");
        return false;
    }

    private void sendForbiddenResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write(String.format(
                "{\"error\": \"Access Denied\", \"message\": \"%s\", \"timestamp\": \"%s\"}",
                message, java.time.Instant.now()
        ));
    }
}
