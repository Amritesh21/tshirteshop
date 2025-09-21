package com.ecommerce.inventoryservice.config;

import com.ecommerce.inventoryservice.utils.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChainBean(HttpSecurity httpSecurity) {
        try {
            return httpSecurity.authorizeHttpRequests( requests ->
              requests.requestMatchers("/api/seller/**").hasAuthority("SELLER")
                      .requestMatchers("/api/buyer/**").hasAuthority("BUYER")
                      .anyRequest().authenticated()
            ).csrf(x -> x.disable()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
