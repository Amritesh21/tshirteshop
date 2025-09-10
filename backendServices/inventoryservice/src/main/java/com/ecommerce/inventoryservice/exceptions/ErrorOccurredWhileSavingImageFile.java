package com.ecommerce.inventoryservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorOccurredWhileSavingImageFile extends RuntimeException {
    public ErrorOccurredWhileSavingImageFile(String message) {
        super(message);
    }
}
