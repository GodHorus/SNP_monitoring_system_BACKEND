//package com.example.master.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.List;
//
////@Configuration
//public class CorsConfig {
//
//
////    @Bean
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////
////        //config.setAllowedOrigins(List.of("*"));
////        config.setAllowedOrigins(List.of(
////                "http://localhost:3000",
////                "http://127.0.0.1:3000",
////                "http://snp-assam.eighteenpixels.com",
////                "https://snp-assam.eighteenpixels.com",
////                "http://snp-assam.eighteenpixels.com:3000",
////                "https://snp-assam.eighteenpixels.com:3000",
////                "http://13.203.237.127",
////                "https://13.203.237.127",
////                "http://13.203.237.127:3000",
////                "https://13.203.237.127:3000",
////                "http://13.233.95.99",
////                "https://13.233.95.99",
////                "http://13.233.95.99:9909",
////                "https://13.233.95.99:9909",
////                "http://aanna-prabah-api.eighteenpixels.in",
////                "https://aanna-prabah-api.eighteenpixels.in",
////                "http://aanna-prabah-api.eighteenpixels.in:9909",
////                "https://aanna-prabah-api.eighteenpixels.in:9909"
////        ));
////        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
////        config.setAllowedHeaders(List.of("*"));
////        config.setAllowCredentials(true);
////
////        source.registerCorsConfiguration("/**", config);
////        return new CorsFilter(source);
////    }
//
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration config = new CorsConfiguration();
////
////        config.setAllowCredentials(true);
////        config.setAllowedOrigins(Arrays.asList(
////                "http://localhost:3000",
////                "http://127.0.0.1:3000",
////                "http://snp-assam.eighteenpixels.com",
////                "https://snp-assam.eighteenpixels.com",
////                "http://snp-assam.eighteenpixels.com:3000",
////                "https://snp-assam.eighteenpixels.com:3000",
////                "http://13.203.237.127",
////                "https://13.203.237.127",
////                "http://13.203.237.127:3000",
////                "https://13.203.237.127:3000",
////                "http://13.233.95.99",
////                "https://13.233.95.99",
////                "http://13.233.95.99:9909",
////                "https://13.233.95.99:9909",
////                "http://aanna-prabah-api.eighteenpixels.in",
////                "https://aanna-prabah-api.eighteenpixels.in",
////                "http://aanna-prabah-api.eighteenpixels.in:9909",
////                "https://aanna-prabah-api.eighteenpixels.in:9909"
////        ));
////
////        config.setAllowedHeaders(Arrays.asList("*"));
////        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
////        config.addExposedHeader("Authorization");
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", config);
////
////        return source;
////    }
//}
