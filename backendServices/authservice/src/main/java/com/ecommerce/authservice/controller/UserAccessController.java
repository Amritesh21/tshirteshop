package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.service.EStoreUserAccessService;
import com.ecommerce.authservice.service.EStoreUserDetailsService;
import com.ecommerce.authservice.service.impl.EStoreAuthenticationProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/verify/token")
    public ResponseEntity<String> verifyToken(HttpServletRequest httpServletRequest) {
        String authToken = httpServletRequest.getHeader("Auth-Token");
        if (ObjectUtils.isEmpty(authToken)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return eStoreUserAccessService.validateAuthToken(authToken);
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
    public ResponseEntity<Boolean> getLoginPage(@RequestBody LoginDTO loginDTO) {
        return eStoreUserAccessService.loginUser(loginDTO);
    }

    @GetMapping("/login")
    public boolean getSocialLogin() {
        return true;
    }

}
