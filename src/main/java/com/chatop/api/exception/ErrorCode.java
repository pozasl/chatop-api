package com.chatop.api.exception;

public enum ErrorCode {
    RESOURCE_NOT_FOUND("ERR-404","Resouce does not exist");

    private String errCode;
    private String errMsg;

    private ErrorCode(final String errCode, final String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrorMsg() {
        return errMsg;
    }
    
}
