package com.example.master.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList(
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
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}

//package com.example.master.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.web.servlet.config.annotation.*;
//@Configuration
//@EnableWebSecurity
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins(
//                        "https://aanna-prabah-api.eighteenpixels.in",
//                        "https://snp-assam.eighteenpixels.com",
//                        "http://13.203.237.127:3000",
//                        "http://localhost:3000",
//                        "http://127.0.0.1:3000"
//                )
//                .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
//                .allowedHeaders("*")
//                .exposedHeaders("Authorization")
//                .allowCredentials(true);
//    }
//}


// below are the changes for updaed https cors errors


//package com.example.master.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class CorsConfig {
//
//    //  Define the CORS configuration here
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of(
//                "https://snp-assam.eighteenpixels.com",
//                "https://aanna-prabah-api.eighteenpixels.in",
//                "http://13.203.237.127:3000",
//                "http://localhost:3000",
//                "http://127.0.0.1:3000"
//        ));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setExposedHeaders(List.of("Authorization"));
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/api/**", config);
//        return source;
//    }
//
//    //  Security filter chain (no deprecated .cors())
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // adjust rules as needed
//        return http.build();
//    }
//}
