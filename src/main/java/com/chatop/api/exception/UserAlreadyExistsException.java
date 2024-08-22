package com.chatop.api.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private final String errorMsg;
    private final String errorCode;

    public UserAlreadyExistsException(ErrorCode code) {
        super(code.getErrMsg());
        errorMsg = code.getErrMsg();
        errorCode = code.getErrCode();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
        errorMsg = ErrorCode.USER_ALREADY_EXISTS.getErrMsg();
        errorCode = ErrorCode.USER_ALREADY_EXISTS.getErrCode();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }   
    
}
