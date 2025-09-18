package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.dto.UserDetailsDTO;

public interface EStoreUserAccessService {

    boolean loginUser(LoginDTO loginDTO);

}
