package com.ecommerce.authservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    @NotEmpty
    String emailId;

    @NotEmpty
    String password;

    @NotEmpty
    String firstName;

    String lastName;

    String address;

    @NotEmpty
    Long phoneNo;

    Long alternateNo;

    @NotEmpty
    List<String> authorities;

}
