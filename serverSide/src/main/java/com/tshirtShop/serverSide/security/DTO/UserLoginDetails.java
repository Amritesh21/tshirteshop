package com.tshirtShop.serverSide.security.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDetails {

    String username;
    String password;

    public UserLoginDetails() {

    }

    public UserLoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
