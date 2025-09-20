package com.ecommerce.authservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LoginTokenEntity {

    @GeneratedValue
    @Id
    private Long id;

    private String token;

    @ManyToOne
    private UserEntity user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUsername(UserEntity user) {
        this.user = user;
    }
}
