package com.example.master.config;

import com.example.master.security.ManualJwtValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final ManualJwtValidationFilter manualJwtValidationFilter;

    public FilterConfig(ManualJwtValidationFilter manualJwtValidationFilter) {
        this.manualJwtValidationFilter = manualJwtValidationFilter;
    }

    @Bean
    public FilterRegistrationBean<ManualJwtValidationFilter> jwtValidationFilter() {
        FilterRegistrationBean<ManualJwtValidationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(manualJwtValidationFilter);
        registrationBean.addUrlPatterns("/api/demands/*");
        registrationBean.setOrder(1); // Execute before other filters
        registrationBean.setName("manualJwtValidationFilter");
        return registrationBean;
    }
}