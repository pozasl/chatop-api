package com.chatop.api.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.chatop.api.model.ResponseMessageInfo;

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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ResponseMessageInfo> handleException(HttpServletRequest request, Exception ex, Locale local) {
        ResponseMessageInfo error = new ResponseMessageInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 404: No route
    @ExceptionHandler({ NoHandlerFoundException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessageInfo> notFound(final NoHandlerFoundException ex) {
        ResponseMessageInfo error = new ResponseMessageInfo(ErrorCode.RESOURCE_NOT_FOUND.getErrMsg());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 404: No resource found
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ResponseMessageInfo> handleResourceNotFoundException(HttpServletRequest request, Exception ex, Locale local) {
        ResponseMessageInfo error = new ResponseMessageInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 404: No static resource found
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ResponseMessageInfo> handleNoResourceFoundException(HttpServletRequest request, Exception ex, Locale local) {
        ResponseMessageInfo error = new ResponseMessageInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 400: Bad equest
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessageInfo> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        ResponseMessageInfo error = new ResponseMessageInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 401: Unauthorized
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<ResponseMessageInfo> handleUsernameNotFoundException(HttpServletRequest request, Exception ex, Locale local) {
        ResponseMessageInfo error = new ResponseMessageInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // 401: Acces denied
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ResponseMessageInfo> handleAccessDeniedException(HttpServletRequest request, Exception ex, Locale local) {
        ResponseMessageInfo error = new ResponseMessageInfo(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
