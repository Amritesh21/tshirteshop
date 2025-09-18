package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.service.EStoreUserAccessService;
import com.ecommerce.authservice.service.EStoreUserDetailsService;
import com.ecommerce.authservice.service.iml.EStoreAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAccessController {

    private final EStoreAuthenticationProvider eStoreAuthenticationProvider;
    private final EStoreUserDetailsService eStoreUserDetailsService;
    private final EStoreUserAccessService eStoreUserAccessService;

    @Autowired
    UserAccessController(EStoreAuthenticationProvider eStoreAuthenticationProvider,
                         EStoreUserDetailsService eStoreUserDetailsService,
                         EStoreUserAccessService eStoreUserAccessService
    ) {
        this.eStoreAuthenticationProvider = eStoreAuthenticationProvider;
        this.eStoreUserDetailsService = eStoreUserDetailsService;
        this.eStoreUserAccessService = eStoreUserAccessService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> createNewUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        boolean userCreated = eStoreUserDetailsService.createNewUser(userDetailsDTO);
        if (userCreated) {
            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(userCreated, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public boolean getLoginPage(@RequestBody LoginDTO loginDTO) {
        return eStoreUserAccessService.loginUser(loginDTO);
    }

    @GetMapping("/login")
    public boolean getLoginPage2() {
        return true;
    }

}
