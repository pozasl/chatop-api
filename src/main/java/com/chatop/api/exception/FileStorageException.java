package com.chatop.api.exception;

public class FileStorageException extends RuntimeException{
    private static final long serialVersionId = 1L;
    private final String errorMsg;
    private final String errorCode;

    public FileStorageException(ErrorCode code) {
        super(code.getErrMsg());
        errorMsg = code.getErrMsg();
        errorCode = code.getErrCode();
    }

    public FileStorageException(String message) {
        super(message);
        errorMsg = ErrorCode.FILE_STORAGE_FAILURE.getErrMsg();
        errorCode = ErrorCode.FILE_STORAGE_FAILURE.getErrCode();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
