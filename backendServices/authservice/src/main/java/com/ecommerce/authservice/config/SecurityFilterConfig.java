package com.ecommerce.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@Configuration
public class SecurityFilterConfig {

    @Bean
    SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity.authorizeHttpRequests((request) ->
                    request.requestMatchers("/api/user/create", "/api/user/signup", "/api/user/login", "/login/index.html", "oauth2/authorization/google", "/.well-known/**")
                            .permitAll()
                            .anyRequest().authenticated()
            ).oauth2Login(x -> x.defaultSuccessUrl("/api/user/login")).csrf(x -> x.disable()).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    InMemoryUserDetailsManager configureAUser() {
        UserDetails user = new User("user", getPasswordEncoder().encode("1234"), new ArrayList<>());
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
