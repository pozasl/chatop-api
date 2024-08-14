package com.chatop.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionId = 1L;
    private final String errorMsg;
    private final String errorCode;

    public ResourceNotFoundException(ErrorCode code) {
        super(code.getErrorMsg());
        errorMsg = code.getErrorMsg();
        errorCode = code.getErrCode();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        errorMsg = ErrorCode.RESOURCE_NOT_FOUND.getErrorMsg();
        errorCode = ErrorCode.RESOURCE_NOT_FOUND.getErrCode();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
