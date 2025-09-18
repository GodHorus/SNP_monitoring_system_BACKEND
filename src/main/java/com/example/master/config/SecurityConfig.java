package com.example.master.config;

import com.example.master.security.KeycloakRealmRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**", "/auth/login", "/auth/forgot-password", "/auth/reset-password").permitAll()
                        .requestMatchers("/api/demands/**","/api/ingredients/**","/api/users/**").authenticated()
                        .anyRequest().authenticated()
                )
                //.cors(cors -> cors.configurationSource())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        //config.setAllowedOrigins(List.of("*"));
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://127.0.0.1:3000",
                "http://snp-assam.eighteenpixels.com",
                "https://snp-assam.eighteenpixels.com",
                "http://snp-assam.eighteenpixels.com:3000",
                "https://snp-assam.eighteenpixels.com:3000",
                "http://13.203.237.127",
                "https://13.203.237.127",
                "http://13.203.237.127:3000",
                "https://13.203.237.127:3000",
                "http://13.233.95.99",
                "https://13.233.95.99",
                "http://13.233.95.99:9909",
                "https://13.233.95.99:9909",
                "http://aanna-prabah-api.eighteenpixels.in",
                "https://aanna-prabah-api.eighteenpixels.in",
                "http://aanna-prabah-api.eighteenpixels.in:9909",
                "https://aanna-prabah-api.eighteenpixels.in:9909"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "https://snp-assam.eighteenpixels.com",
                "http://localhost:3000"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//
//        config.setAllowedOrigins(Arrays.asList(
//                "https://snp-assam.eighteenpixels.com",  // frontend domain
//                "http://13.203.237.127:3000",            // frontend IP
//                "http://localhost:3000",                 // local dev
//                "http://127.0.0.1:3000"                  // local dev
//        ));
//
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        config.setAllowedHeaders(Arrays.asList("*"));
//        config.addExposedHeader("Authorization");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // applies to all endpoints
//
//        return source;
//    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter("snp-backend"));
        return converter;
    }
}

//package com.example.master.config;
//
//import com.example.master.security.KeycloakRealmRoleConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.core.convert.converter.Converter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> {
//                })
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/api/**").permitAll()
//                                .requestMatchers("/api/fci").permitAll()
//                                .requestMatchers("/api/districts").permitAll()
//                                .requestMatchers("/api/supplier").permitAll()
//                                .requestMatchers("/api/ingredients/lab-report").permitAll()
//                                .requestMatchers("/api/batches").permitAll()
//                                .requestMatchers("/api/awc-dispatches/**").permitAll()
//                                .requestMatchers("/api/ingredients").authenticated()
//                                .requestMatchers("/api/batches").permitAll()
//                                .requestMatchers("/api/demands/**").authenticated()
//                                .requestMatchers("/api/users/**").authenticated()
//                                .requestMatchers("/api/**").permitAll()
//                                .requestMatchers("/auth/login").permitAll()
//                                .requestMatchers("/auth/forgot-password").permitAll()
//                                .requestMatchers("/auth/reset-password").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                );
//
//        return http.build();
//    }
//
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter("snp-backend"));
//        return converter;
//    }
//}
