package com.chatop.api.model;

public interface ResponseMessageFactory {

    ResponseMessageFactory setMessage(String message);
    ResponseMessage build() ;
}
