package com.ecommerce.inventoryservice.exceptions;

import com.ecommerce.inventoryservice.dto.error.ErrorResponseDTO;
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

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ErrorResponseDTO> invalidProductIdException(InvalidProductIdException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageFileAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> imageFileAlreadyExistsException(ImageFileAlreadyExistsException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageFileNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> imageFileNotFoundException(ImageFileNotFoundException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> genericException(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .path(request.getDescription(false))
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

}
