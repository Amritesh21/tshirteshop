package com.tshirtShop.serverSide.security.config;

import java.util.List;
import java.util.stream.Collectors;

import com.tshirtShop.serverSide.security.POJO.Authority;
import com.tshirtShop.serverSide.security.POJO.UserEntity;
import com.tshirtShop.serverSide.security.repository.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EStoreUserDetailsManager implements UserDetailsService {

    @Autowired
    UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && !username.equals("")) {
            UserEntity userEntity = userEntityService.getUserByUsername(username);
            if (userEntity != null) {
                List<GrantedAuthority> grantedAuthorityList = getAllGrantedAuthorities(userEntity.getAuthorities());
                return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorityList);
            }
        }
        return null;
    }

    private List<GrantedAuthority> getAllGrantedAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorityList = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority_name()))
                .collect(Collectors.toList());
        return grantedAuthorityList;
    }

}
