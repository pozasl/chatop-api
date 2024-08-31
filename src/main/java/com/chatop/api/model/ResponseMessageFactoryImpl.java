package com.chatop.api.model;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessageFactoryImpl implements ResponseMessageFactory {

    @Override
    public ResponseMessage create(String message) {
            return new ResponseMessageInfo(message);
    }
}
