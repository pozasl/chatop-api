package com.chatop.api.model;

/**
 * Response message factory interface.
 */
public interface ResponseMessageFactory {

  ResponseMessage makeResponseMessage(String message);
  
}
