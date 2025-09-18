package com.ecommerce.authservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    private String authority;

    @ManyToMany(mappedBy = "authority")
    private List<UserEntity> users = new ArrayList<>();

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public void setAuthority_name(String authority) {
        this.authority = authority;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

