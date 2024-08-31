package com.chatop.api.model;

/**
 * Response message factory implementation.
 */
public class ResponseMessageFactoryImpl implements ResponseMessageFactory {

  ResponseMessageFactoryImpl() {}

  public static ResponseMessageFactory create() {
    return new ResponseMessageFactoryImpl();
  }

  public ResponseMessage makeResponseMessage(String message) {
    return new ResponseMessageInfo(message);
  }

}