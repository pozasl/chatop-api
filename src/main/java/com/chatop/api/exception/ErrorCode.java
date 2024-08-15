package com.chatop.api.exception;

public enum ErrorCode {
    RESOURCE_NOT_FOUND("ERR-001","Resouce does not exist"),
    INVALID_ARGUMENT("ERR-002","Invalid argment");

    private String errCode;
    private String errMsg;

    private ErrorCode(final String errCode, final String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
    
}
