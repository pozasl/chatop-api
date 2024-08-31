package com.chatop.api.model;

public class ResponseMessageFactoryImpl implements ResponseMessageFactory {

    ResponseMessageFactoryImpl() {};

    public static ResponseMessageFactory create() {
        return new ResponseMessageFactoryImpl();
    }

    public ResponseMessage makeResponseMessage(String message) {
        return new ResponseMessageInfo(message);
    }

}