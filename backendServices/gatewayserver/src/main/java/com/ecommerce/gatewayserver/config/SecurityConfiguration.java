package com.ecommerce.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityWebFilterChain getFilterChainBean(ServerHttpSecurity httpSecurity){
        return httpSecurity.authorizeExchange(x -> x.pathMatchers("/**").permitAll()).build();
    }

}
