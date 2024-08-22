package com.chatop.api.exception;

public enum ErrorCode {
    RESOURCE_NOT_FOUND("ERR-001","Resouce does not exist"),
    INVALID_ARGUMENT("ERR-002","Invalid argment"),
    FILE_STORAGE_FAILURE("ERR-003","Couldn't save file"),
    USER_ALREADY_EXISTS("ERR-004","This email is already registered");

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
