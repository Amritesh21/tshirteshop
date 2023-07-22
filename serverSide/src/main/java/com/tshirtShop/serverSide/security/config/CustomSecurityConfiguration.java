package com.tshirtShop.serverSide.security.config;

import com.tshirtShop.serverSide.security.filters.JWTAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class CustomSecurityConfiguration {

    @Bean
    public SecurityFilterChain configureFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .addFilterBefore(new JWTAuthFilter(configureAPIWhiteListing()), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/public/*").permitAll()
                .antMatchers("/auth/*").authenticated();
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder configurePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public APIWhiteListing configureAPIWhiteListing() {
        APIWhiteListing apiWhiteListing = new APIWhiteListing();
        apiWhiteListing.whiteListAPI("/api/public", "unauthenticated");
        apiWhiteListing.whiteListAPI("/api/auth", "authenticated");
        return apiWhiteListing;
    }
}
