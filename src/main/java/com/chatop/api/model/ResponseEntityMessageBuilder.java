package com.chatop.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseEntityMessageBuilder {

    ResponseEntityMessageBuilder setMessage(String message);
    ResponseEntityMessageBuilder setStatus(HttpStatus status);
    ResponseEntity<ResponseMessage> build();
    
}
