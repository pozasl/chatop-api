package com.chatop.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityMessageBuilderImpl implements ResponseEntityMessageBuilder{

    private String message;
    private HttpStatus status;
    private ResponseMessageFactory responseMessageFactory;

    ResponseEntityMessageBuilderImpl(ResponseMessageFactoryImpl responseMessageFactory) {
        this.responseMessageFactory = responseMessageFactory;
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
        ResponseMessage responseMessage =  responseMessageFactory.create(message);
        return new ResponseEntity<ResponseMessage>(responseMessage, status);
    }
    
}
