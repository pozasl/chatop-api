package com.chatop.api.exception;

/**
 * API's exception interface.
 */
public interface ApiException {

  String getErrorMsg();

  String getErrorCode();
}