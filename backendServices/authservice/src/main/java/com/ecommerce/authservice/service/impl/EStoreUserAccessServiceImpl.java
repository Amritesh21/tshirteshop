package com.ecommerce.authservice.service.impl;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.entity.LoginTokenEntity;
import com.ecommerce.authservice.repository.EStoreLoginTokenRepo;
import com.ecommerce.authservice.service.EStoreUserAccessService;
import com.ecommerce.authservice.utils.jwts.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EStoreUserAccessServiceImpl implements EStoreUserAccessService {

    private final EStoreAuthenticationProvider eStoreAuthenticationProvider;
    private final JwtHelper jwtHelper;
    private final EStoreUserDetailsManager eStoreUserDetailsManager;
    private final EStoreLoginTokenRepo eStoreLoginTokenRepo;

    @Autowired
    public EStoreUserAccessServiceImpl(EStoreAuthenticationProvider eStoreAuthenticationProvider,
                                       JwtHelper jwtHelper,
                                       EStoreUserDetailsManager eStoreUserDetailsManager,
                                       EStoreLoginTokenRepo eStoreLoginTokenRepo
    ) {
        this.eStoreAuthenticationProvider = eStoreAuthenticationProvider;
        this.jwtHelper = jwtHelper;
        this.eStoreUserDetailsManager = eStoreUserDetailsManager;
        this.eStoreLoginTokenRepo = eStoreLoginTokenRepo;
    }

    @Override
    public ResponseEntity<Boolean> loginUser(LoginDTO loginDTO) {
        System.out.println("logindto");
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication validatedAuth = eStoreAuthenticationProvider.authenticate(authentication);
        if (validatedAuth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(validatedAuth);
            String jwToken = jwtHelper.generateNewAuthToken();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("auth-token", jwToken);
            httpHeaders.add("Content-Type", "application/json");
            return ResponseEntity.ok().headers(httpHeaders).body(true);
        } else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");
            return ResponseEntity.badRequest().headers(httpHeaders).body(false);
        }
    }

    @Override
    public ResponseEntity<String> validateAuthToken(String authToken) {
        Claims claims = jwtHelper.parseJwt(authToken);
        String username = jwtHelper.getUsername(claims);
        Boolean isTokenExpired = jwtHelper.isTokenExpired(claims);
        boolean isTokenLoggedIn = eStoreLoginTokenRepo.findByUserUsername(username).stream().map(x -> x.getToken()).filter(x -> x.equals(authToken)).findAny().isEmpty();
        if (!ObjectUtils.isEmpty(username) && !isTokenExpired && !isTokenLoggedIn) {
            UserDetails userDetails = eStoreUserDetailsManager.loadUserByUsername(username);
            String bearerToken = jwtHelper.generateBearerToken(userDetails.getUsername(), (List<GrantedAuthority>) userDetails.getAuthorities());
            return ResponseEntity.ok().header("Bearer-Token", bearerToken).body(bearerToken);
        } else if (isTokenExpired) {
            eStoreLoginTokenRepo.deleteByToken(authToken);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }


}
