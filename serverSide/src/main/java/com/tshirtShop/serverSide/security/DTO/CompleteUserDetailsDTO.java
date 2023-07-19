package com.tshirtShop.serverSide.security.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteUserDetailsDTO {

    private String username;
    private String firstName;
    private String lastName;
    private long phoneNo;
    private long alternatePhoneNo;
    private String address;

    public CompleteUserDetailsDTO() {}

    public CompleteUserDetailsDTO(String username, String firstName, String lastName, long phoneNo, long alternatePhoneNo, String address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.alternatePhoneNo = alternatePhoneNo;
        this.address = address;
    }

}
