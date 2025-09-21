package com.ecommerce.orderservice.config;

import com.ecommerce.orderservice.utils.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity.authorizeHttpRequests(requests ->
                    requests.requestMatchers("/api/buyer/**").hasAuthority("BUYER").anyRequest().permitAll()
            ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).csrf(x -> x.disable()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
