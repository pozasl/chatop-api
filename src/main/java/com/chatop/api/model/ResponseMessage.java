package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response message interface.
 */
public interface ResponseMessage {
  @JsonProperty("message")
  String message();
}
