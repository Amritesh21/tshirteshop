package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.UserDetailsDTO;

public interface EStoreUserDetailsService {

    boolean createNewUser(UserDetailsDTO userDetailsDTO);

    boolean updateUser(UserDetailsDTO userDetailsDTO);

    UserDetailsDTO getUserDetailsByUsername(String username);

}
