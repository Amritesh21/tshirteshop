package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.constants.ExceptionMessages;
import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.service.EStoreUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDetailsManagementController {

    private EStoreUserDetailsService eStoreUserDetailsService;

    UserDetailsManagementController(EStoreUserDetailsService eStoreUserDetailsService) {
        this.eStoreUserDetailsService = eStoreUserDetailsService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<Boolean> createNewUser(@RequestBody UserDetailsDTO userDetailsDTO) {
//        boolean userCreated = eStoreUserDetailsService.createNewUser(userDetailsDTO);
//        if (userCreated) {
//            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(userCreated, HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        boolean userUpdated = eStoreUserDetailsService.updateUser(userDetailsDTO);
        if (userUpdated) {
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userUpdated, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable String username) {
       UserDetailsDTO userDetailsDTO = eStoreUserDetailsService.getUserDetailsByUsername(username);
       if (ObjectUtils.isEmpty(userDetailsDTO)) {
           throw new RuntimeException(ExceptionMessages.INVALID_USERNAME.getMessage());
       } else {
           return new ResponseEntity<UserDetailsDTO>(userDetailsDTO, HttpStatus.OK);
       }
    }



}
