package com.ecommerce.gatewayserver.config;

import com.ecommerce.gatewayserver.utils.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    AuthTokenFilter authTokenFilter;

    @Bean
    SecurityWebFilterChain getFilterChainBean(ServerHttpSecurity httpSecurity){
        return httpSecurity
                .authorizeExchange(x -> x.pathMatchers("/auth/api/signup", "/auth/api/login").
                        permitAll().anyExchange().authenticated())
                .csrf(x -> x.disable())
                .addFilterBefore(authTokenFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

}
