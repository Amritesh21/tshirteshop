package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.service.iml.EStoreAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAccessController {

    EStoreAuthenticationProvider eStoreAuthenticationProvider;

    @Autowired
    UserAccessController(EStoreAuthenticationProvider eStoreAuthenticationProvider) {
        this.eStoreAuthenticationProvider = eStoreAuthenticationProvider;
    }

    @RequestMapping("/signup")
    public String getSignupPage() {
        return "signup/index";
    }

    @RequestMapping("/login")
    public String getLoginPage(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication validatedAuth = eStoreAuthenticationProvider.authenticate(authentication);
        if (validatedAuth.isAuthenticated()) {
            return "Authentication successful";
        } else {
            return "Authentication unsuccessful";
        }
    }

}
