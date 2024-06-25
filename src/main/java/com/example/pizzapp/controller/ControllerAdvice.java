package com.example.pizzapp.controller;

import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerResourceNotFound(ResourceNotFoundException e) {
        return ErrorResponse.builder(e,
                HttpStatus.NOT_FOUND,
                e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception e) {
        return ErrorResponse.builder(e,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An unexpected error occurred")
                        .build();
    }

    @ExceptionHandler(DuplicateFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handlerDuplicateFound(DuplicateFoundException e) {
        return ErrorResponse.builder(e,
                        HttpStatus.NOT_ACCEPTABLE,
                        e.getMessage())
                .build();
    }


}