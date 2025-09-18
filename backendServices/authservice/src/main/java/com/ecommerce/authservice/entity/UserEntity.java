package com.ecommerce.authservice.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity implements UserDetails {

    @Id
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @ManyToMany
    private List<Authority> authority = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    CompleteUserEntity completeUserEntity;

    public UserEntity () {
    }

    public UserEntity( String username, String password, List<Authority> authorities, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.authority = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserEntity( String username, String password, List<Authority> authorities, String firstName, String lastName, CompleteUserEntity completeUserEntity) {
        this.username = username;
        this.password = password;
        this.authority = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
        this.completeUserEntity = completeUserEntity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authority;
    }

    public void setAuthorities(Authority authority) {
        this.authority.add(authority);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CompleteUserEntity getCompleteUserEntity() {
        return completeUserEntity;
    }

    public void setCompleteUserEntity(CompleteUserEntity completeUserEntity) {
        this.completeUserEntity = completeUserEntity;
    }
}