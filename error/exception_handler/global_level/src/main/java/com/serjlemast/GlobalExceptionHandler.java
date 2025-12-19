package com.serjlemast;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 *  1. Target all Controllers annotated with @RestController:
 *     @ControllerAdvice(annotations = RestController.class)
 *
 *  2. Target all Controllers within specific packages:
 *     @ControllerAdvice("org.example.controllers")
 *
 *  3. Target all Controllers assignable to specific classes
 *     @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerLevelException.class)
    public ErrorResponse errorControllerHandler(Exception e) {
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, "bad message").build();
    }

    @ExceptionHandler(ServiceLevelException.class)
    public ErrorResponse errorServiceHandler(Exception e) {
        return ErrorResponse.builder(e, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "illegal person").build();
    }
}
