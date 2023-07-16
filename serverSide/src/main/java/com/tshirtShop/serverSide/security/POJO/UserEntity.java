package com.tshirtShop.serverSide.security.POJO;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @GeneratedValue
    private String user_id;

    @Id
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @ManyToMany
    private List<Authority> authority = new ArrayList<Authority>();

    public UserEntity () {
    }

    public UserEntity( String username, String password, List<Authority> authorities, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.authority = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
