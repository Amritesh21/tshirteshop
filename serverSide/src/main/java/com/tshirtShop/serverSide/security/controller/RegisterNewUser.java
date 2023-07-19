package com.tshirtShop.serverSide.security.controller;

import com.tshirtShop.serverSide.security.DTO.*;
import com.tshirtShop.serverSide.security.POJO.Authority;
import com.tshirtShop.serverSide.security.POJO.CompleteUserEntity;
import com.tshirtShop.serverSide.security.POJO.UserEntity;
import com.tshirtShop.serverSide.security.repository.UserEntityService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
public class RegisterNewUser {

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("api/public/create/user")
    public ResponseEntity<String> registerNewUser(@RequestBody NewUserDTO newUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(newUserDTO.getUserName());
        userEntity.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        userEntity.setFirstName(newUserDTO.getFirstName());
        userEntity.setLastName(newUserDTO.getLastName());
        newUserDTO.getAuthorities().stream().forEach(authority -> userEntity.setAuthorities(new Authority(authority)));
        if (userEntityService.addUser(userEntity)) {
            return ResponseEntity.ok().body("User successfully added");
        }
        return ResponseEntity.badRequest().body("Invalid input provided");
    }

    @PostMapping("api/public/create/authority")
    public ResponseEntity<String> registerNewAuthority(@RequestBody NewAuthority newAuthority) {
        Authority authority = new Authority();
        authority.setAuthority_name(newAuthority.getAuthorityName());
        if (userEntityService.addAuthority(authority)) {
            return ResponseEntity.ok().body("Authority successfully added");
        } else {
            return ResponseEntity.ok().body("Invalid input provided");
        }
    }

    @PostMapping("api/public/user/login")
    public ResponseEntity<LoggedInUserDTO> userLoginValidation(@RequestBody UserLoginDetails userLoginDetails) {
        Authentication enteredAuthObj = new UsernamePasswordAuthenticationToken(userLoginDetails.getUsername(), userLoginDetails.getPassword());
        Authentication validatedAuthObj =  authenticationProvider.authenticate(enteredAuthObj);
        String jwToken = null;
        if (validatedAuthObj.isAuthenticated()) {
            jwToken = Jwts.builder()
                    .setIssuer("Mangcoding")
                    .setSubject("Login authentication")
                    .claim("username", validatedAuthObj.getName())
                    .claim("authorities", validatedAuthObj.getAuthorities())
                    .signWith(Keys.hmacShaKeyFor("jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4".getBytes(StandardCharsets.UTF_8)))
                    .compact();
            SecurityContext securityContext = new SecurityContextImpl();
            securityContext.setAuthentication(validatedAuthObj);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("auth-token", jwToken);
            UserEntity loginUserDetails = userEntityService.getUserByUsername(validatedAuthObj.getName());
            LoggedInUserDTO loggedInUserDTO = new LoggedInUserDTO(loginUserDetails.getUsername(), loginUserDetails.getFirstName(), loginUserDetails.getLastName());
            loggedInUserDTO.setLogInMessage("Authentication successful");
            return ResponseEntity.ok().headers(httpHeaders).body(loggedInUserDTO);
        }
        final Supplier<LoggedInUserDTO> errorMessageSupplier = () -> { LoggedInUserDTO loggedInUserDTO = new LoggedInUserDTO(); loggedInUserDTO.setLogInMessage("Invalid credentials"); return loggedInUserDTO; };
        return ResponseEntity.ok().body(errorMessageSupplier.get());
    }

    @PostMapping("/api/public/update/user")
    public CompleteUserDetailsDTO setUserDetails(@RequestBody CompleteUserDetailsDTO completeUserDetailsDTO) {
        CompleteUserEntity completeUserEntity = new CompleteUserEntity();
        UserEntity userEntity = userEntityService.getUserByUsername(completeUserDetailsDTO.getUsername());
        CompleteUserEntity existingCompleteUserEntity = userEntityService.getCompleteUserByUsername(completeUserDetailsDTO.getUsername());
        if (userEntity != null) {
            if (existingCompleteUserEntity != null ){
                completeUserEntity = existingCompleteUserEntity;
            }
            completeUserEntity.setAddress(completeUserDetailsDTO.getAddress());
            completeUserEntity.setPhoneNo(completeUserDetailsDTO.getPhoneNo());
            completeUserEntity.setAlternatePhoneNo(completeUserDetailsDTO.getAlternatePhoneNo());
            completeUserEntity.setUsername(completeUserDetailsDTO.getUsername());
            completeUserEntity.setUserEntity(userEntity);
            userEntity.setFirstName(completeUserDetailsDTO.getFirstName());
            userEntity.setLastName(completeUserDetailsDTO.getLastName());
            userEntityService.addUser(userEntity);
            if(userEntityService.setCompleteUserDetails(completeUserEntity) == true) {
                return completeUserDetailsDTO;
            }
        }
        return null;
    }

    @GetMapping("/api/public/user/complete/info/{username}")
    public CompleteUserDetailsDTO getUserCompleteDetails(@PathVariable String username) {
        UserEntity userEntity = userEntityService.getUserByUsername(username);
        CompleteUserDetailsDTO completeUserDetailsDTO = new CompleteUserDetailsDTO();
        CompleteUserEntity completeUserEntity = userEntityService.getCompleteUserByUsername(username);
        completeUserDetailsDTO.setFirstName(userEntity.getFirstName());
        completeUserDetailsDTO.setLastName(userEntity.getLastName());
        completeUserDetailsDTO.setUsername(completeUserEntity.getUsername());
        completeUserDetailsDTO.setPhoneNo(completeUserEntity.getPhoneNo());
        completeUserDetailsDTO.setAlternatePhoneNo(completeUserEntity.getAlternatePhoneNo());
        completeUserDetailsDTO.setAddress(completeUserEntity.getAddress());
        return completeUserDetailsDTO;
    }

}
