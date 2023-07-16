package com.tshirtShop.serverSide.security.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewUserDTO {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> authorities;

}
