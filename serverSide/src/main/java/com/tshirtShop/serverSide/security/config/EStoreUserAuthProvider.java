package com.tshirtShop.serverSide.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EStoreUserAuthProvider implements AuthenticationProvider {

    @Autowired
    EStoreUserDetailsManager eStoreUserDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication != null) {
            String enteredUsername = authentication.getName();
            String enteredPassword = authentication.getCredentials().toString();
            UserDetails userDetails = eStoreUserDetailsManager.loadUserByUsername(enteredUsername);
            if (userDetails != null && passwordEncoder.matches(enteredPassword, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("Invalid user details entered");
            }
        } else {
            throw new BadCredentialsException("Invalid user details entered");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
