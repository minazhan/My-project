package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.service.impl.AuthenticationFilter;

@Configuration
public class FilterConfig {
	
    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistration() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/index.html", "/admin/*", "/rest/user", "/rest/transaction", "/api/recommend");
        return registrationBean;
    }
}
