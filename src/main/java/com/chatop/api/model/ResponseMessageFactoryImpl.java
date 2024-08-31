package com.chatop.api.model;

public class ResponseMessageFactoryImpl implements ResponseMessageFactory {

    private String message;
    
    ResponseMessageFactoryImpl() {
        this.message = "Ok";
    };

    public static ResponseMessageFactory create() {
        return new ResponseMessageFactoryImpl();
    }

    @Override
    public ResponseMessageFactory setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseMessage build() {
        return new ResponseMessageInfo(message);
    }

}