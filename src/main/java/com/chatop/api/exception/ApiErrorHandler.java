package com.chatop.api.exception;

import com.chatop.api.model.ResponseEntityMessageBuilder;
import com.chatop.api.model.ResponseEntityMessageBuilderImpl;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.model.ResponseMessageFactoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global API error handling.
 */
@ControllerAdvice
public class ApiErrorHandler {

  private ResponseEntityMessageBuilder reponseMessageBuilder;

  /**
   * Constructor.
   */
  ApiErrorHandler() {
    // Avoid spring injection for easier test
    this.reponseMessageBuilder = new ResponseEntityMessageBuilderImpl(
      ResponseMessageFactoryImpl.create()
    );
  }

  // 500
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ResponseEntity<ResponseMessage> handleException(final Exception ex) {
    return reponseMessageBuilder
        .setMessage(ex.getMessage())
        .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();
  }

  /**
   * 404: No route no handler.
   *
   * @param ex the exception to handle
   * @return Error message response
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ResponseMessage> handleNoHandlerFoundException(final Exception ex) {
    return reponseMessageBuilder
        .setMessage(ErrorCode.RESOURCE_NOT_FOUND.getErrMsg())
        .setStatus(HttpStatus.NOT_FOUND)
        .build();
  }

  /**
   * 404: No static resource found or can't find in db.
   *
   * @param ex the exception to handle
   * @return Error message response
   */
  @ExceptionHandler({ NoResourceFoundException.class, ResourceNotFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ResponseEntity<ResponseMessage> handleNoResourceFoundException(final Exception ex) {
    return reponseMessageBuilder
        .setMessage(ex.getMessage())
        .setStatus(HttpStatus.NOT_FOUND)
        .build();
  }

  /**
   * 404: Bad request.
   *
   * @param ex the exception to handle
   * @return Error message response
   */
  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseMessage> handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException ex) {
    return reponseMessageBuilder
        .setMessage(ex.getMessage())
        .setStatus(HttpStatus.BAD_REQUEST)
        .build();
  }

  /**
   * 401: Unauthorized.
   *
   * @param ex the exception to handle
   * @return Error message response
   */
  @ExceptionHandler({
    UsernameNotFoundException.class,
    AccessDeniedException.class,
    BadCredentialsException.class
  })
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ResponseEntity<ResponseMessage> handleUnauthorizedException(final Exception ex) {
    return reponseMessageBuilder
        .setMessage(ex.getMessage())
        .setStatus(HttpStatus.UNAUTHORIZED)
        .build();
  }

  /**
   * 409: Conflict (register twice).
   *
   * @param ex the exception to handle
   * @return Error message response
   */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(UserAlreadyExistsException.class)
  ResponseEntity<ResponseMessage> handleUserAlreadyExistsException(final Exception ex) {
    return reponseMessageBuilder
        .setMessage(ex.getMessage())
        .setStatus(HttpStatus.CONFLICT)
        .build();
  }

}
