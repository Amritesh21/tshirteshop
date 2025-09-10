package com.ecommerce.inventoryservice.constants;

public enum ExceptionMessages {

    CreateDirectoryException("Error occurred while creating directory"),
    SaveImageFileException("Error occurred while saving file"),
    ProductAlreadyExists("A product already exists with the entered name please change the name and try again");

    final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
