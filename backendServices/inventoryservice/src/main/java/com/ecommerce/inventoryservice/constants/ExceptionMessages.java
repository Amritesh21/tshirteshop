package com.ecommerce.inventoryservice.constants;

import com.ecommerce.inventoryservice.exceptions.ImageFileAlreadyExistsException;

public enum ExceptionMessages {

    CreateDirectoryException("Error occurred while creating directory."),
    SaveImageFileException("Error occurred while saving file."),
    ProductAlreadyExists("A product already exists with the entered name please change the name and try again."),
    InvalidProductIdEntered("Entered product id is not associated with any product. This event will be reported."),
    ImageFileAlreadyExistsException("An image file with same name is already uploaded for this product"),
    ImageFileNotFoundException("There is no image file with the given name"),
    ErrorWhileReadingImageFile("Error occurred while reading image file. Please contact your system admin");

    final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
