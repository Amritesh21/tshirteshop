package com.ecommerce.inventoryservice.exceptions;

import java.io.IOException;

public class ErrorOccurredWhileCreatingDirectory extends RuntimeException {
    public ErrorOccurredWhileCreatingDirectory(String message) {
        super(message);
    }
}
