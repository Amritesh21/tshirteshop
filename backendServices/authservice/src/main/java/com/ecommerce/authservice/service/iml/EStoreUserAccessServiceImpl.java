package com.ecommerce.authservice.service.iml;

import com.ecommerce.authservice.dto.LoginDTO;
import com.ecommerce.authservice.service.EStoreUserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class EStoreUserAccessServiceImpl implements EStoreUserAccessService {

    private EStoreAuthenticationProvider eStoreAuthenticationProvider;

    @Autowired
    public EStoreUserAccessServiceImpl(EStoreAuthenticationProvider eStoreAuthenticationProvider) {
        this.eStoreAuthenticationProvider = eStoreAuthenticationProvider;
    }

    @Override
    public boolean loginUser(LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication validatedAuth = eStoreAuthenticationProvider.authenticate(authentication);
        if (validatedAuth.isAuthenticated()) {
            return true;
        } else {
            return false;
        }
    }
}
