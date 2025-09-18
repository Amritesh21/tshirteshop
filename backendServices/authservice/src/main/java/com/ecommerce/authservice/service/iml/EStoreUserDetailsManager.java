package com.ecommerce.authservice.service.iml;

import com.ecommerce.authservice.entity.UserEntity;
import com.ecommerce.authservice.repository.EStoreUserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class EStoreUserDetailsManager implements UserDetailsService {

    private EStoreUserDetailsRepo userDetailsRepo;

    @Autowired
    EStoreUserDetailsManager(EStoreUserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!ObjectUtils.isEmpty(username)) {
            Optional<UserEntity> userDetailsOptional = userDetailsRepo.findById(username);
            if (userDetailsOptional.isPresent()) {
                return userDetailsOptional.get();
            }
        }
        return null;
    }
}
