package com.ecommerce.authservice.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class EStoreAuthenticationProvider implements AuthenticationProvider {

    private final EStoreUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    EStoreAuthenticationProvider(EStoreUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!ObjectUtils.isEmpty(authentication)) {
            String enteredUsername = authentication.getName();
            String enteredPassword = authentication.getCredentials().toString();
            UserDetails userDetails = userDetailsManager.loadUserByUsername(enteredUsername);

            if (!ObjectUtils.isEmpty(userDetails) && userDetails.getPassword().matches(enteredPassword)) {
                return new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
