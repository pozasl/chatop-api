package com.chatop.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityMessageBuilderImpl implements ResponseEntityMessageBuilder{

    private HttpStatus status;
    private ResponseMessageFactory responseMessageFactory;

    public ResponseEntityMessageBuilderImpl(ResponseMessageFactory responseMessageFactory) {
        this.responseMessageFactory = responseMessageFactory;
        this.status = HttpStatus.OK;
    }

    @Override
    public ResponseEntityMessageBuilder setMessage(String message) {
        responseMessageFactory.setMessage(message);
        return this;
    }

    @Override
    public ResponseEntityMessageBuilder setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public ResponseEntity<ResponseMessage> build() {
        ResponseMessage responseMessage =  responseMessageFactory.build();
        return new ResponseEntity<ResponseMessage>(responseMessage, status);
    }
}
