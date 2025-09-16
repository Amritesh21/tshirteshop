package com.ecommerce.inventoryservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageFileAlreadyExistsException extends RuntimeException {
    public ImageFileAlreadyExistsException(String message) {
        super(message);
    }
}
