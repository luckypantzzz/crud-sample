package com.nikolaev.controller;

import com.nikolaev.service.UserNameDuplicateException;
import com.nikolaev.service.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNameDuplicateException.class, UserNotFoundException.class})
    public ResponseEntity handle(Exception ex, WebRequest request) {
        HttpStatus status;
        if (ex instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UserNameDuplicateException) {
            status = HttpStatus.CONFLICT;
        } else {
            return super.handleException(ex, request);
        }
        return new ResponseEntity(status);
    }
}
