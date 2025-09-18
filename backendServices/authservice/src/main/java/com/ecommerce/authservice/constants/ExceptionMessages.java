package com.ecommerce.authservice.constants;

public enum ExceptionMessages {

    INVALID_USERNAME("Invalid username entered"),
    INVALID_AUTHORITY_ASSOCIATED("Invalid authority associated with user"),
    USERNAME_ALREADY_PRESENT("Entered username is already registered");

    private String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
