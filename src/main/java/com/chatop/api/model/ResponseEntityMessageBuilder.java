package com.chatop.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Builder for http response entity with a message model.
 */
public interface ResponseEntityMessageBuilder {
  
  ResponseEntityMessageBuilder setMessage(String message);

  ResponseEntityMessageBuilder setStatus(HttpStatus status);

  ResponseEntity<ResponseMessage> build();
}
