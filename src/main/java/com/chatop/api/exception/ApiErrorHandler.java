package com.chatop.api.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiErrorHandler {
    
    private final MessageSource messageSource;

    @Autowired
    public ApiErrorHandler(MessageSource source) {
        messageSource = source;
    }

    // 500
    @ExceptionHandler(Exception.class)
    ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex, Locale local) {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 404: No route
    @ExceptionHandler({ NoHandlerFoundException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Error> notFound(final NoHandlerFoundException ex) {
        Error error = new Error(ErrorCode.RESOURCE_NOT_FOUND.getErrorMsg());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 404: No resource found
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Error> handleResourceNotFoundException(HttpServletRequest request, Exception ex, Locale local) {
        Error error = new Error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
