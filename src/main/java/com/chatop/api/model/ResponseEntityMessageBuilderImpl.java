package com.chatop.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Response entity message builder implementation.
 */
public class ResponseEntityMessageBuilderImpl implements ResponseEntityMessageBuilder {

  private HttpStatus status;
  private String message;
  private ResponseMessageFactory responseMessageFactory;

  public ResponseEntityMessageBuilderImpl(ResponseMessageFactory responseMessageFactory) {
    this.responseMessageFactory = responseMessageFactory;
    status = HttpStatus.OK;
  }

  @Override
  public ResponseEntityMessageBuilder setMessage(String message) {
    this.message = message;
    return this;
  }

  @Override
  public ResponseEntityMessageBuilder setStatus(HttpStatus status) {
    this.status = status;
    return this;
  }

  @Override
  public ResponseEntity<ResponseMessage> build() {
    ResponseMessage responseMessage = responseMessageFactory.makeResponseMessage(message);
    return new ResponseEntity<ResponseMessage>(responseMessage, status);
  }
}
