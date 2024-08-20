package com.chatop.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    // private static final long serialVersionId = 1L;
    private final String errorMsg;
    private final String errorCode;

    public ResourceNotFoundException(ErrorCode code) {
        super(code.getErrMsg());
        errorMsg = code.getErrMsg();
        errorCode = code.getErrCode();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        errorMsg = ErrorCode.RESOURCE_NOT_FOUND.getErrMsg();
        errorCode = ErrorCode.RESOURCE_NOT_FOUND.getErrCode();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
