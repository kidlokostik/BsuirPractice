package com.example.pizzapp.controller;

import com.example.pizzapp.dto.response.error.ErrorResponse;
import com.example.pizzapp.dto.response.error.MultiErrorResponse;
import com.example.pizzapp.exception.AccessDeniedException;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.util.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;

import static com.example.pizzapp.util.ErrorMessages.*;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerResourceNotFound(ResourceNotFoundException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception e) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(INTERNAL_SERVER_ERROR_MESSAGE)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MultiErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return MultiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(VALIDATION_FAILED_MESSAGE)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HTTP_MESSAGE_NOT_READABLE_MESSAGE)
                .build();
    }

    @ExceptionHandler(DuplicateFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handlerDuplicateFound(DuplicateFoundException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(ErrorMessages.ACCESS_DENIED_MESSAGE)
                .build();
    }
}