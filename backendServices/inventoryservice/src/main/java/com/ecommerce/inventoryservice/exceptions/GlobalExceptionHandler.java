package com.ecommerce.inventoryservice.exceptions;

import com.ecommerce.inventoryservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorOccurredWhileCreatingDirectory.class)
    public ResponseEntity<ErrorResponseDTO> handleDirectoryCreationFailed(ErrorOccurredWhileCreatingDirectory ex, WebRequest request) {
         ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_GATEWAY.toString()).build();

         return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorOccurredWhileSavingImageFile.class)
    public ResponseEntity<ErrorResponseDTO> handleSaveFileFailed(ErrorOccurredWhileSavingImageFile ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString()).build();

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> productAlreadyExists(ProductAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString()).build();

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

}
