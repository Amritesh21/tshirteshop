package com.tshirtShop.serverSide.security.DTO;

public class LoggedInUserDTO {

    private String username, firstName, lastName, logInMessage, userType;

    public LoggedInUserDTO() {
    }

    public LoggedInUserDTO(String username, String firstName, String lastName, String userType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public String getLogInMessage() {
        return logInMessage;
    }

    public void setLogInMessage(String logInMessage) {
        this.logInMessage = logInMessage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoggedInUserDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", logInMessage='" + logInMessage + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
