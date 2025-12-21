package com.serjlemast;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean<SecondFilter> filterRegistrationBean() {
        FilterRegistrationBean<SecondFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SecondFilter());
        registration.addUrlPatterns("/admin/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<UserSecurityFilter> filterRegistrationBean2() {
        FilterRegistrationBean<UserSecurityFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserSecurityFilter());
        registration.addUrlPatterns("/users/*");
        registration.setOrder(2);
        return registration;
    }
}
