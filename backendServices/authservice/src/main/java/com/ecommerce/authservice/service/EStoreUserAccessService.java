package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.dto.UserDetailsDTO;
import org.springframework.http.ResponseEntity;

public interface EStoreUserAccessService {

    ResponseEntity<Boolean> loginUser(LoginDTO loginDTO);

    ResponseEntity<String> validateAuthToken(String authToken);

}
