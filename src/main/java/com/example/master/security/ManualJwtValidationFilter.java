package com.example.master.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Manual JWT Role Validation Filter
 * This acts as a backup security layer when Spring Security's @PreAuthorize fails
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

        // Check if this is a DELETE request to demands endpoint
        if ("DELETE".equals(method) && requestURI.matches("/api/demands/\\d+")) {
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
            // Parse JWT manually (simple approach - in production use proper JWT library)
            String[] parts = jwtToken.split("\\.");
            if (parts.length != 3) {
                logger.error("Invalid JWT token format");
                return false;
            }

            // Decode payload (second part)
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
            logger.debug("JWT Payload: {}", payload);

            JsonNode jsonNode = objectMapper.readTree(payload);

            // Check multiple possible locations for admin role
            return checkAdminRole(jsonNode);

        } catch (Exception e) {
            logger.error("Error parsing JWT token: {}", e.getMessage());
            return false;
        }
    }

    private boolean checkAdminRole(JsonNode jsonNode) {
        // Check realm_access.roles
        JsonNode realmAccess = jsonNode.get("realm_access");
        if (realmAccess != null && realmAccess.get("roles") != null) {
            for (JsonNode role : realmAccess.get("roles")) {
                String roleValue = role.asText().toUpperCase();
                logger.debug("Found realm role: {}", roleValue);
                if ("ADMIN".equals(roleValue)) {
                    logger.info("Admin role found in realm_access.roles");
                    return true;
                }
            }
        }

        // Check resource_access for each client
        JsonNode resourceAccess = jsonNode.get("resource_access");
        if (resourceAccess != null) {
            resourceAccess.fields().forEachRemaining(entry -> {
                String clientName = entry.getKey();
                JsonNode clientData = entry.getValue();
                JsonNode clientRoles = clientData.get("roles");

                if (clientRoles != null) {
                    for (JsonNode role : clientRoles) {
                        String roleValue = role.asText().toUpperCase();
                        logger.debug("Found client {} role: {}", clientName, roleValue);
                        if ("ADMIN".equals(roleValue)) {
                            logger.info("Admin role found in resource_access.{}.roles", clientName);
                        }
                    }
                }
            });
        }

        // Check direct roles claim
        JsonNode directRoles = jsonNode.get("roles");
        if (directRoles != null) {
            for (JsonNode role : directRoles) {
                String roleValue = role.asText().toUpperCase();
                logger.debug("Found direct role: {}", roleValue);
                if ("ADMIN".equals(roleValue)) {
                    logger.info("Admin role found in direct roles claim");
                    return true;
                }
            }
        }

        // Check groups (sometimes used instead of roles)
        JsonNode groups = jsonNode.get("groups");
        if (groups != null) {
            for (JsonNode group : groups) {
                String groupValue = group.asText().toUpperCase();
                logger.debug("Found group: {}", groupValue);
                if ("ADMIN".equals(groupValue)) {
                    logger.info("Admin role found in groups claim");
                    return true;
                }
            }
        }

        // Check email-based admin (fallback for your specific setup)
        JsonNode email = jsonNode.get("email");
        if (email != null) {
            String emailValue = email.asText();
            logger.debug("User email: {}", emailValue);
            if ("snp@gmail.com".equals(emailValue)) {
                logger.info("Admin role granted based on email: {}", emailValue);
                return true;
            }
        }

        // Check preferred_username (fallback)
        JsonNode username = jsonNode.get("preferred_username");
        if (username != null) {
            String usernameValue = username.asText();
            logger.debug("Username: {}", usernameValue);
            // Add username-based admin check if needed
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